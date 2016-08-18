package com.feifanuniv.management.entity;

public class LJson {
	private String id;
	private String key;
	private String value;
	private String temp;
	private String count;

	public LJson() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getKey() {
		return key;
	}
	public void yyy(String s){
		this.id=s;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getTemp() {
		return temp;
	}

	public void setTemp(String temp) {
		this.temp = temp;
	}

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}

	@Override
	public String toString() {
		return "LJson [key=" + key + ", value=" + value + ", temp=" + temp + ", count=" + count + "]";
	}

	
}
