package com.feifanuniv.management.entity;

public class Base {

	public int id;
	public String keyid;
	public String status;


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getKeyid() {
		return keyid;
	}
	
	public void setKeyid(String keyid) {
		this.keyid = keyid;
	}
	
	public Base(){
	}
	
	public Base(int id){
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}
