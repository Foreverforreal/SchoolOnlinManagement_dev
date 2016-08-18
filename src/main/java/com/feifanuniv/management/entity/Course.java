package com.feifanuniv.management.entity;

public class Course {
	private int id;
	private int global_id;
	private String name;
	private int global_courseware_id;
	private String base_url;
	private boolean courseware_status;
	private boolean courseware_view_type;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getGlobal_id() {
		return global_id;
	}
	public void setGlobal_id(int global_id) {
		this.global_id = global_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getGlobal_courseware_id() {
		return global_courseware_id;
	}
	public void setGlobal_courseware_id(int global_courseware_id) {
		this.global_courseware_id = global_courseware_id;
	}
	public String getBase_url() {
		return base_url;
	}
	public void setBase_url(String base_url) {
		this.base_url = base_url;
	}
	public boolean isCourseware_status() {
		return courseware_status;
	}
	public void setCourseware_status(boolean courseware_status) {
		this.courseware_status = courseware_status;
	}
	public boolean isCourseware_view_type() {
		return courseware_view_type;
	}
	public void setCourseware_view_type(boolean courseware_view_type) {
		this.courseware_view_type = courseware_view_type;
	}
	
	@Override
	public String toString() {
		return "Course [id=" + id + ", global_id=" + global_id + ", name=" + name + ", global_courseware_id="
				+ global_courseware_id + ", base_url=" + base_url + ", courseware_status=" + courseware_status
				+ ", courseware_view_type=" + courseware_view_type + "]";
	}
	
	

}
