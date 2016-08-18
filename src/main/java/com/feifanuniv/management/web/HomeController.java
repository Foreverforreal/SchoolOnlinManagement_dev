package com.feifanuniv.management.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.feifanuniv.management.service.TeachingPlanService;
import com.feifanuniv.management.service.XueShengService;
import com.feifanuniv.management.utils.PropertityUtils;

@Controller
public class HomeController extends BaseController {
	@RequestMapping("/Home")
	public String goHome(){
		return "home";
	}
	@RequestMapping("/TeachingplanDownload")
	public String goTeachingplanDownload(){
		return "teachingplanDownload";
	}
	@RequestMapping("/Xuesheng*")
	public String goXuesheng(){
		return "xueSheng";
	}
	@RequestMapping("/School*")
	public String goSchool(HttpServletRequest request){
		PropertityUtils.setDbConfigPath(BaseController.setconfig(request.getParameter("env")));
		
		SchoolTeachingplanController.resetSchoolTeachingPlanService((TeachingPlanService) getApplicationContext().getBean("schoolService"));
		StudentController.resetXueShengService((XueShengService) getApplicationContext().getBean("xueShengService"));
		return "school";
	}
	@RequestMapping("/teachingplan*")
	public String goTeachingplan(){
		return "newTeachingplan";
	}
	
	@RequestMapping("/GoUpdateXuesheng*")
	public String goUpdateXuesheng(){
		return "updateXuesheng";
	}
	@RequestMapping("/GoCopyTeachingplan*")
	public String goCopyTeachingplan(){
		return "addMissingTeachingplan";
	}
	@RequestMapping("/GoQueryTeachingplan*")
	public String goQueryTeachingplan(){
		return "teachingplanQurey";
	}
	@RequestMapping("/GoUpdateJxjh*")
	public String goUpdateUpdateJxjh(HttpServletRequest request ,HttpServletResponse response){
		if(request.getParameter("type").equals("new")){
			request.setAttribute("to", "new");
		}else if(request.getParameter("type").equals("replenish")){
			request.setAttribute("to", "replenish");
		}
		return "updateJxjh";
	}
}
