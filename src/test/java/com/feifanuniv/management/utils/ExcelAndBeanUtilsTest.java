package com.feifanuniv.management.utils;

import static org.junit.Assert.fail;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.feifanuniv.management.entity.XueSheng;
import com.feifanuniv.management.utils.ExcelAndBeanUtils;

public class ExcelAndBeanUtilsTest {
	private FileInputStream fileInputStream;

	@Before
	public void setUp() throws Exception {
		ExcelAndBeanUtils.setVersionAndReceiveEntity("1.xlsx", XueSheng.class);
		this.fileInputStream = new FileInputStream("C:\\Users\\zhu\\Desktop\\test\\学生信息.xlsx");
	}

	@Test
	public void testReadExcel() throws Exception   {
		
		List<XueSheng> list = null;
		
		if (fileInputStream != null) {
			list = ExcelAndBeanUtils.readExcel(fileInputStream);
		}
		for (XueSheng xuesheng : list) {
			System.out.println(xuesheng);
		}
	}

	@Test
	public void test() throws Exception {
		List<XueSheng> list = null;
		
		if (fileInputStream != null) {
			list = ExcelAndBeanUtils.readExcel(fileInputStream);
		}
		ObjectMapper objectMapper = new ObjectMapper();
		JsonGenerator jsonGenerator=objectMapper.getFactory().createGenerator(System.out);
		jsonGenerator.writeObject(list);
        System.out.println("ObjectMapper");
	}
	
	@Test
	public void testGetRowIterator() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetXlxsSheet() throws IOException {
		fail("Not yet implemented");
	}

	@Test
	public void testGetXlsSheet() {
		
	}

	@Test
	public void testPutToEntityList() {
		fail("Not yet implemented");
	}

	@Test
	public void testPuToEntity() {
		fail("Not yet implemented");
	}

}
