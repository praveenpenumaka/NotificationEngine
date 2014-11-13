package com.engine.core;

import java.util.HashMap;
import java.util.Iterator;

import org.json.JSONObject;

public class Contact {
	
	private String first_name;
	
	private String last_name;
	
	private String short_name;
	
	private String email;
	
	private String phone;
	
	private String other;
	
	private HashMap<String,String> params = null;
	
	public Contact(String contactJson) throws Exception{
		if(contactJson=="" || contactJson.isEmpty()){
			return;
		}
		JSONObject j = new JSONObject(contactJson);
		Iterator<String> keyI = j.keys();
		if(keyI.hasNext()){
			params = new HashMap<String,String>();
		}
		while(keyI.hasNext()){
			String key = keyI.next();
			String value = j.get(key).toString();
			this.params.put(key,value);
		}
	}

	public String getEmail() {
		if( this.params != null && this.params.containsKey("email")){
			return this.params.get("email");
		}
		return null;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		if( this.params != null && this.params.containsKey("phone")){
			return this.params.get("phone");
		}
		return null;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getOther() {
		return other;
	}

	public void setOther(String other) {
		this.other = other;
	}

	public String getFirst_name() {
		if( this.params != null && this.params.containsKey("first_name")){
			return this.params.get("first_name");
		}
		return null;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		if( this.params != null && this.params.containsKey("last_name")){
			return this.params.get("last_name");
		}
		return null;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getShort_name() {
		if( this.params != null && this.params.containsKey("short_name")){
			return this.params.get("short_name");
		}
		return null;
	}
	
	public String getFull_name(){
		if( this.params != null && this.params.containsKey("full_name")){
			return this.params.get("full_name");
		}
		return null;		
	}

	public void setShort_name(String short_name) {
		this.short_name = short_name;
	}

	public String getPageId() {
		if( this.params != null && this.params.containsKey("page_id")){
			return this.params.get("page_id");
		}
		return null;		
	}

	
}
