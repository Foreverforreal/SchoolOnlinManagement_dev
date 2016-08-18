package com.feifanuniv.management.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellValue;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.util.LocaleUtil;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExcelAndBeanUtils {
	
	private static String version="0";
	private static Class<?> entityType;

	public static void setVersionAndReceiveEntity(String fileName,Class<?> entity){
		
		entityType=entity;
		
		if(fileName.endsWith("xlsx")){
			version="0";
		}else if(fileName.endsWith("xls")){
			version="1";
		}else{
			version="3";
		}
	}
	public static <T> List<T> readExcel(InputStream inputStream)   {
		Iterator<Row> ir = null;
		try {
			ir = ExcelAndBeanUtils.getRowIterator(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return putToEntityList(ir);
		
	}
	
	
	public static XSSFSheet getXlxsSheet(InputStream inputStream) throws IOException {
		@SuppressWarnings("resource")
		XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
		XSSFSheet sheet = workbook.getSheetAt(0);
		return sheet;
	}

	public static HSSFSheet getXlsSheet(InputStream inputStream) throws IOException {
		@SuppressWarnings("resource")
		HSSFWorkbook workbook = new HSSFWorkbook(inputStream);
		HSSFSheet sheet = workbook.getSheetAt(0);
		return sheet;
	}
	
	public static Iterator<Row> getRowIterator(InputStream inputStream) throws IOException {
		
		switch (version) {
		case "0":
			return getXlxsSheet(inputStream).rowIterator();
		case "1":
			return getXlsSheet(inputStream).rowIterator();
		}
		
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public static <T> ArrayList<T> putToEntityList(Iterator<Row> ir)  {
		ArrayList<T> arraylist = new ArrayList<>();
		while (ir.hasNext()) {
			Row row = ir.next();
			arraylist.add((T) puToEntity(row));
		}
		return arraylist;
	}

	@SuppressWarnings("unchecked")
	public static <T> T puToEntity(Row row)  {
		String[] fields = ReflectUtils.getAllFieldName(entityType);
		if(row.getLastCellNum()>fields.length){
			System.out.println("excel列数不对");
		}
		T entity = null;
		try {
			entity = (T) entityType.newInstance();
			for (int i = 0; i < fields.length; i++) {
				if(i==0){
					if(toString(row.getCell(i))==null){
						break;
					};
				}
				Cell cell = row.getCell(i);
				BeanUtils.setProperty(entity, fields[i], toString(cell));
			}
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}

		return entity;

	}
	
	public static String toString(Cell c) {
		if(c==null){
			return null;
		}
		switch (c.getCellType()) {
		case Cell.CELL_TYPE_BLANK:
			return null;
		case Cell.CELL_TYPE_NUMERIC:
			if (DateUtil.isCellDateFormatted((Cell) c)) {
				DateFormat sdf = new SimpleDateFormat("yyyymmdd", LocaleUtil.getUserLocale());
				sdf.setTimeZone(LocaleUtil.getUserTimeZone());
				return sdf.format(c.getDateCellValue()).trim().replace(" ","");
			}
			DecimalFormat format = new DecimalFormat("#");
			format.setRoundingMode(RoundingMode.DOWN);
			return format.format( c.getNumericCellValue()).trim().replace(" ","");
		case Cell.CELL_TYPE_STRING:
			String value=c.getRichStringCellValue().toString();
			if(value.equals("")){
				return null;
			}
			return value.trim().replace("（", "(").replace("）", ")").replace(" ","");
		case Cell.CELL_TYPE_FORMULA:
				FormulaEvaluator evaluator = c.getSheet().getWorkbook().getCreationHelper().createFormulaEvaluator();
			    evaluator.evaluateFormulaCell(c);
			    CellValue cellValue = evaluator.evaluate(c);
			    return cellValue.getStringValue().replace(" ","") ;
		default:
			return "Unknown Cell Type: " + c.getCellType();
		}
	}

	public static File generateExcel(List<?> list ,String filePath,String schoolSymbol,String type){
			XSSFWorkbook workbook = new XSSFWorkbook();
			XSSFSheet sheet = workbook.createSheet();
			FileOutputStream stream=null;
			String fileStorePath=null;
		try {
			fileStorePath=generateFilePath(filePath,schoolSymbol,type);
			stream = new FileOutputStream(fileStorePath);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		putObjectToRow(list,sheet);
		
		try {
			workbook.write(stream);
			stream.close();
			workbook.close();
			return new File(fileStorePath);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public static String generateFilePath(String originPath,String schoolSymbol,String type){
		String newPath = null;
		switch (type) {
		case "xuesheng":
			newPath= ("E:\\未导入学生\\"+schoolSymbol+"未导入学生  "+originPath);
			break;
		case "teachingplan":
			newPath= ("E:\\下载的教学计划\\"+schoolSymbol+" "+originPath);
			break;
		default:
			break;
		}
		if(newPath.endsWith(".xls")){
			return newPath.replace(".xls", ".xlsx");
		}else{
			newPath=newPath.replace(".xlsx", "")+".xlsx";
			
		}
		return newPath;
	}
	
	public static void putObjectToRow(List<?> list,XSSFSheet sheet){
		for (int i = 0; i < list.size(); i++) {
			XSSFRow row = sheet.createRow(i);
			String[] fileds=splitString(list.get(i).toString());
			for (int j = 0; j < fileds.length; j++) {
				row.createCell(j).setCellValue(fileds[j]);
			}
		}
	}
	public static String[] splitString(String s){
		String[] done=s.substring(s.indexOf('[')+1, s.indexOf(']')).split(",");
		for (int i = 0; i < done.length; i++) {
			done[i]=done[i].substring(done[i].indexOf('=')+1);
			if("null".equals(done[i])){
				done[i]="";
			}
		}
		return done;
	}
}
