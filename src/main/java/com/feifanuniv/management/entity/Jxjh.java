package com.feifanuniv.management.entity;

public class Jxjh extends Base{
	private String major;
	private String level;
	private String name;
	private String term;
	private String enroll_year;
	private int  res_course;
	private int  res_courseware;
	private String mappingName;
	private String url;
	private String ebook;
	private String quiz;
	private String optional;
	public String getMajor() {
		return major;
	}
	public void setMajor(String major) {
		this.major = major;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTerm() {
		return term;
	}
	public void setTerm(String term) {
		this.term = term;
	}
	public String getEnroll_year() {
		return enroll_year;
	}
	public void setEnroll_year(String enroll_year) {
		this.enroll_year = enroll_year;
	}
	public int getRes_course() {
		return res_course;
	}
	public void setRes_course(int res_course) {
		this.res_course = res_course;
	}
	public int getRes_courseware() {
		return res_courseware;
	}
	public void setRes_courseware(int res_courseware) {
		this.res_courseware = res_courseware;
	}
	public String getMappingName() {
		return mappingName;
	}
	public void setMappingName(String mappingName) {
		this.mappingName = mappingName;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getEbook() {
		return ebook;
	}
	public void setEbook(String ebook) {
		this.ebook = ebook;
	}
	public String getQuiz() {
		return quiz;
	}
	public void setQuiz(String quiz) {
		this.quiz = quiz;
	}
	public String getOptional() {
		return optional;
	}
	public void setOptional(String optional) {
		this.optional = optional;
	}
	@Override
	public String toString() {
		return "Jxjh [major=" + major + ", level=" + level + ", name=" + name + ", term=" + term + ", enroll_year="
				+ enroll_year + ", res_course=" + res_course + ", res_courseware=" + res_courseware + ", mappingName="
				+ mappingName + ", url=" + url + ", ebook=" + ebook + ", quiz=" + quiz + ", optional=" + optional + "]";
	}
	
	
}
