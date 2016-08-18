package com.feifanuniv.management.entity;



public class Major extends Base {
	private String name;
	private String level;
	private int level_sequence;
	private String introduction;
	private String introduction_short;
	private String cover_image_id;
	private boolean show_in_home_page;
	private String global_major_id;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public int getLevel_sequence() {
		return level_sequence;
	}
	public void setLevel_sequence(int level_sequence) {
		this.level_sequence = level_sequence;
	}
	public String getIntroduction() {
		return introduction;
	}
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}
	public String getIntroduction_short() {
		return introduction_short;
	}
	public void setIntroduction_short(String introduction_short) {
		this.introduction_short = introduction_short;
	}
	public String getCover_image_id() {
		return cover_image_id;
	}
	public void setCover_image_id(String cover_image_id) {
		this.cover_image_id = cover_image_id;
	}
	public boolean isShow_in_home_page() {
		return show_in_home_page;
	}
	public void setShow_in_home_page(boolean show_in_home_page) {
		this.show_in_home_page = show_in_home_page;
	}
	public String getGlobal_major_id() {
		return global_major_id;
	}
	public void setGlobal_major_id(String global_major_id) {
		this.global_major_id = global_major_id;
	}
	@Override
	public String toString() {
		return "Major [name=" + name + ", level=" + level + ", level_sequence=" + level_sequence + ", introduction="
				+ introduction + ", introduction_short=" + introduction_short + ", cover_image_id=" + cover_image_id
				+ ", show_in_home_page=" + show_in_home_page + ", global_major_id=" + global_major_id + "]";
	}
	
	
	
}
