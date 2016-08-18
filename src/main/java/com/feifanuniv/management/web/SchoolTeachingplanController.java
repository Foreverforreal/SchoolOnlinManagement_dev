package com.feifanuniv.management.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.feifanuniv.management.entity.Jxjh;
import com.feifanuniv.management.entity.LJson;
import com.feifanuniv.management.entity.Major;
import com.feifanuniv.management.service.TeachingPlanService;
import com.feifanuniv.management.utils.WrongException;
import com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException;

@Controller
public class SchoolTeachingplanController extends BaseController{
	static TeachingPlanService schoolTeachingPlanService=(TeachingPlanService) getApplicationContext().getBean("schoolService");
	@RequestMapping("/NewSchool*") 
	public void addSchoolAndCheckJxjh(HttpServletRequest request ,HttpServletResponse response ) throws FileUploadException, IOException, WrongException {
			
		schoolTeachingPlanService.setFileItemAndSwitchDb(getFileupload().parseRequest(request));
		if(schoolTeachingPlanService.addSchool()){
			schoolTeachingPlanService.createAndInsertJxjh();
			response.getWriter().write("ok");
		}else{
			response.getWriter().write("buok");
		}
	}

	@RequestMapping("/GetBreakJxjh*")
	public @ResponseBody List<Jxjh> getBreakJxjh() {
		return schoolTeachingPlanService.getBreakJxjh();
	}
	@RequestMapping("/GetMappingGlobalMajor*")
	public @ResponseBody List<Major> getMappingGlobalMajor() {
		return schoolTeachingPlanService.makeSureMajor();
	}
	@RequestMapping("/UpdateJxjh*")
	public String  updateXueSheng(HttpServletRequest request ,HttpServletResponse response) {
		System.out.println("请求");
		HashMap<String, String> map =new HashMap<>();
		map.put("newMajor", request.getParameter("newMajor"));
		map.put("oldMajor", request.getParameter("oldMajor"));
		map.put("newLevel", request.getParameter("newLevel"));
		map.put("oldLevel", request.getParameter("oldLevel"));
		map.put("newCourse", request.getParameter("newCourse"));
		map.put("oldCourse", request.getParameter("oldCourse"));
		map.put("newYear", request.getParameter("newYear"));
		map.put("oldYear", request.getParameter("oldYear"));
		schoolTeachingPlanService.modifyJxjh(map);
		return "update_jxjh";
	}
	@RequestMapping("/TeachingplanOk*")
	public @ResponseBody String insertTeachingplan() {
		schoolTeachingPlanService.insertTeachingplan();
		return "success";
	}
	@RequestMapping("/AddTeachingplan*")
	public @ResponseBody List<LJson> addTeachingplan() {
		List<LJson> exsitTeachingplanCourse=schoolTeachingPlanService.addTeachingplan();
		 if(exsitTeachingplanCourse==null){
			 return null;
		 }
		return exsitTeachingplanCourse;
	}
	
	

	@RequestMapping("/GetSchoolSymbol*")
	public @ResponseBody List<LJson> getSchoolSymbol() {
		return schoolTeachingPlanService.getComBoxList("schoolSymbol","");
	}
	@RequestMapping("/GetGlobalMajor*")
	public @ResponseBody List<LJson> getGlobalMajor() {
		return schoolTeachingPlanService.getComBoxList("globalmajor","");
	}
	@RequestMapping("/GetJxjhMajor*")
	public @ResponseBody List<LJson> getJxjhMajor() {
		return schoolTeachingPlanService.getComBoxList("major","");
	}
	@RequestMapping("/GetJxjhCourse*")
	public @ResponseBody List<LJson> getJxjhCourse() {
		return schoolTeachingPlanService.getComBoxList("course","");
	}
	@RequestMapping("/GetJxjhLevel*")
	public @ResponseBody List<LJson> getJxjhLevel() {
		return schoolTeachingPlanService.getComBoxList("level","");
	}
	@RequestMapping("/GetJxjhYear*")
	public @ResponseBody List<LJson> getJxjhYear() {
		return schoolTeachingPlanService.getComBoxList("enroll_year","");
	}
	@RequestMapping("/GetTeachingplanMajor*")
	public @ResponseBody List<LJson> getTeachingplanMajor() {
		return schoolTeachingPlanService.getComBoxList("t_major","");
	}
	@RequestMapping("/GetTeachingplanTerm*")
	public @ResponseBody List<LJson> getJxjhTerm() {
		return schoolTeachingPlanService.getComBoxList("t_term","");
	}
	@RequestMapping("/GetTeachingplanLevel*")
	public @ResponseBody List<LJson> getTeachingplanLevel(String major) {
		return schoolTeachingPlanService.getComBoxList("t_level",major);
	}
	@RequestMapping("/GetTeachingplanYear*")
	public @ResponseBody List<LJson> getTeachingplanYear(String majorId) {
		return schoolTeachingPlanService.getComBoxList("t_year",majorId);
	}
	
	
	@RequestMapping("/temp1*")
	public void temp1(HttpServletRequest request ,HttpServletResponse response ) throws FileUploadException, IOException, WrongException, MySQLSyntaxErrorException {
		
		List<LJson> list=schoolTeachingPlanService.getComBoxList("schoolSymbol","");
		
		for (int i = 0; i < list.size(); i++) {
			schoolTeachingPlanService.resetConneUrl(list.get(i).getValue());
			schoolTeachingPlanService.commonSearch(list.get(i).getValue());
			if(i%30==0){
				schoolTeachingPlanService.printMajorStudent(String.valueOf(i));
			}else if(i==list.size()-1){
				schoolTeachingPlanService.printMajorStudent(String.valueOf(i));
			}
		}
	}
	@RequestMapping("/temp*")
	public void temp(HttpServletRequest request ,HttpServletResponse response ) throws FileUploadException, IOException, WrongException, MySQLSyntaxErrorException {
		
		List<LJson> list=schoolTeachingPlanService.getComBoxList("schoolSymbol","");
		for (LJson lJson : list) {
			schoolTeachingPlanService.resetConneUrl(lJson.getValue());
			schoolTeachingPlanService.commonSearch(lJson.getKey());
		}
		
		schoolTeachingPlanService.printMajorStudent("all");
	}
	
	
	
	@RequestMapping("/TeachingplanReplenish*")
	public void teachingplanReplenish(HttpServletRequest request ,HttpServletResponse response ) throws FileUploadException, IOException, WrongException {
		
		
		schoolTeachingPlanService.setFileItemAndSwitchDb(getFileupload().parseRequest(request));
		schoolTeachingPlanService.dealWithUploadFile();
		schoolTeachingPlanService.createAndInsertJxjh();
	}
	
	@RequestMapping("/ChooseSchool*")
	public @ResponseBody String chooseSchool(String schoolSymbol)  {
		schoolTeachingPlanService.resetConneUrl(schoolSymbol);
		return "connect";
	}
	@RequestMapping("/QueryTeachingplan*")
	public @ResponseBody List<Jxjh> getTeachingplan(LJson ljson)  {
		return	schoolTeachingPlanService.getTeachingplan(ljson);
	}
	@RequestMapping("/DownloadTeachingplan*")
	public @ResponseBody String  downloadTeachingplan(String schoolSymbol)  {
		schoolTeachingPlanService.downloadTeachingplan(schoolSymbol);
		return "ok"; 
	}
	@RequestMapping("/GetTeachingplanCourseInsertCount*")
	public @ResponseBody int  getTeachingplanCourseInsertCount()  {	
		return schoolTeachingPlanService.getInsertTeachingplanCourseCount();
	}

	public static void resetSchoolTeachingPlanService(TeachingPlanService tps) {
		schoolTeachingPlanService = tps;
	}
	
	
}
