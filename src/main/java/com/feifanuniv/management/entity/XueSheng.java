package com.feifanuniv.management.entity;

public class XueSheng extends Base{

	private String major;
	private String level;
	private String center;
	private String user_name;
	private String password;
	private String display_name;
	private String gender;
	private String student_number;
	private String identity_card;
	private String enroll_year;
	private String phone_num;
	private String address;
	
	public XueSheng() {
		super();
	}

	public XueSheng(String major, String level, String center, String user_name, String password, String display_name,
			String gender, String student_number, String identity_card, String enroll_year, String phone,
			String address) {
		super();
		this.major = major;
		this.level = level;
		this.center = center;
		this.user_name = user_name;
		this.password = password;
		this.display_name = display_name;
		this.gender = gender;
		this.student_number = student_number;
		this.identity_card = identity_card;
		this.enroll_year = enroll_year;
		this.phone_num = phone;
		this.address = address;
	}

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

	public String getCenter() {
		return center;
	}

	public void setCenter(String center) {
		this.center = center;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDisplay_name() {
		return display_name;
	}

	public void setDisplay_name(String display_name) {
		this.display_name = display_name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getStudent_number() {
		return student_number;
	}

	public void setStudent_number(String student_number) {
		this.student_number = student_number;
	}

	public String getIdentity_card() {
		return identity_card;
	}

	public void setIdentity_card(String identity_card) {
		this.identity_card = identity_card;
	}

	public String getEnroll_year() {
		return enroll_year;
	}

	public void setEnroll_year(String enroll_year) {
		this.enroll_year = enroll_year;
	}

	public String getPhone_num() {
		return phone_num;
	}

	public void setPhone_num(String phone_num) {
		this.phone_num = phone_num;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String adderss) {
		this.address = adderss;
	}

	@Override
	public String toString() {
		return "XueSheng [major=" + major + ", level=" + level + ", center=" + center + ", user_name=" + user_name
				+ ", password=" + password + ", display_name=" + display_name + ", gender=" + gender
				+ ", student_number=" + student_number + ", identity_card=" + identity_card + ", enroll_year="
				+ enroll_year + ", phone_num=" + phone_num + ", address=" + address + "]";
	}


	
	
}
