package com.feifanuniv.management.entity;

public class Global_user extends Base {
	
	private String user_name;
	private String display_name;
	private String password;
	private String gender;
	private String phone_num;
	private String address;
	private String idnetity_card;
	private String user_type;
	public Global_user() {
		super();
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getDisplay_name() {
		return display_name;
	}
	public void setDisplay_name(String display_name) {
		this.display_name = display_name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
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
	public void setAddress(String address) {
		this.address = address;
	}
	public String getIdnetity_card() {
		return idnetity_card;
	}
	public void setIdnetity_card(String idnetity_card) {
		this.idnetity_card = idnetity_card;
	}
	public String getUser_type() {
		return user_type;
	}
	public void setUser_type(String user_type) {
		this.user_type = user_type;
	}
	
	@Override
	public String toString() {
		return "Global_user [user_name=" + user_name + ", display_name=" + display_name + ", password=" + password
				+ ", gender=" + gender + ", phone_num=" + phone_num + ", address=" + address + ", idnetity_card="
				+ idnetity_card + ", user_type=" + user_type + "]";
	}
	
	
	
	
	
}
