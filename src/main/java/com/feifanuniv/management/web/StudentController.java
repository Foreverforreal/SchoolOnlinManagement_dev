package com.feifanuniv.management.web;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.feifanuniv.management.entity.LJson;
import com.feifanuniv.management.entity.XueSheng;
import com.feifanuniv.management.service.XueShengService;
import com.feifanuniv.management.utils.WrongException;

@Controller
public class StudentController extends BaseController {
	static XueShengService xueShengService=(XueShengService) getApplicationContext().getBean("xueShengService");
	
	@RequestMapping("/xueshengCheck*")
	public void parseAndImportXuesheng(HttpServletRequest request ,HttpServletResponse response ) throws FileUploadException, IOException, WrongException {
			
		xueShengService.setFileItemAndSwitchDb(getFileupload().parseRequest(request));
		if(xueShengService.insertXuesheng()>0){
			throw new WrongException();
		};
	}
	
	@RequestMapping("/GetNoMappingXueSheng*")
	public @ResponseBody List<XueSheng> getNoMappingXueShengList() {
		return xueShengService.selecMappingMajorXueSheng();
	}
	
	@RequestMapping("/UpdateXueSheng*")
	public void  updateXueSheng(HttpServletRequest request ,HttpServletResponse response) {
		HashMap<String, String> map =new HashMap<>();
		map.put("newMajor", request.getParameter("newMajor"));
		map.put("oldMajor", request.getParameter("oldMajor"));
		map.put("newLevel", request.getParameter("newLevel"));
		map.put("oldLevel", request.getParameter("oldLevel"));
		xueShengService.modifyXuesheng(map);
	}
	@RequestMapping("/StudentOK*")
	public @ResponseBody String insertGlobalUserAndStudent(HttpServletRequest request ,HttpServletResponse response) {
		xueShengService.FlitrateAndinsertGlobalUser();
		xueShengService.insertUserSchool();
		xueShengService.insertStudent();
		return "ok";
	}
	@RequestMapping("/DownloanNoMappingXuesheng*")
	public ResponseEntity<byte[]> downloanNoMappingXuesheng() throws IOException {
		File downloadExcel=xueShengService.downloadExccel();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentDispositionFormData("attachment", new String(downloadExcel.getName().getBytes("UTF-8"),"iso-8859-1"));
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM); 
		return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(downloadExcel),    
                 headers, HttpStatus.CREATED);
	}
	
	@RequestMapping("/NoMappingTeachingPlanXueSheng*")
	public @ResponseBody List<LJson> selectNoMappingTeachingPlanXueSheng() {
	
		return  xueShengService.selectNoMappingTeachingPlanXueSheng();
		
	}
	
	@RequestMapping("/GetTeachingplan*")
	public @ResponseBody List<LJson> getTeachingplan(HttpServletRequest request) {
		return xueShengService.getComBoxList("teachingplan",request.getParameter("majorid"));
	}
	@RequestMapping("/CopyTeachingplan*")
	public void copyTeachingplan(HttpServletRequest request) {
	}
	
	@RequestMapping("/getMajor*")
	public @ResponseBody List<LJson> getMajor() {
		return xueShengService.getComBoxList("major","");
	}
	@RequestMapping("/getLevel*")
	public @ResponseBody List<LJson> getMajorlevel(HttpServletRequest request) {
		return xueShengService.getComBoxList("level",request.getParameter("majorname"));
	}
	@RequestMapping("/getDistinctMajor*")
	public @ResponseBody List<LJson> getDistinctMajor() {
		return xueShengService.getComBoxList("distinctmajor","");
	}
	@RequestMapping("/getDistinctLevel*")
	public @ResponseBody List<LJson>  getDistinctLevel() {
		return xueShengService.getComBoxList("distinctlevel","");
	}
	
	@RequestMapping("/GetExistMajorLevel*")
	public @ResponseBody List<LJson>  getExistMajorLevel() {
		return xueShengService.getComBoxList("existmajorlevel","");
	}


	public static void resetXueShengService(XueShengService xss) {
		xueShengService = xss;
	}
	
	
}
