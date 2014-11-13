package com.engine.core;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.engine.core.Notification.Channels;

public class NotificationRequest {
	
	private Date timestamp; 
	
	private HashMap<String,String> params;

	private String rawJson;

	private JSONObject jsonObject;
	
	
	public void initializeFromJson(String json) throws Exception{
		if( json == null || json.isEmpty()){
			return;
		}
		this.rawJson = json;
		
		
		JSONObject j = new JSONObject(this.rawJson);
		
		this.jsonObject = j;
		
		JSONArray channels = (JSONArray)j.get("channels");
//		JSONArray a = (JSONArray)j.get("data");
//		for(int i =0;i<a.length();i++){
//			JSONObject p = (JSONObject)a.get(i);			
//		}
	}
	
	public boolean isValid() {
		if( rawJson == null || rawJson.isEmpty() || jsonObject == null ){
			return false;
		}
		return true;
	}

	public String getMode() {
		if( params != null && params.containsKey("mode")){
			return params.get("mode");
		}
		return null;
	}

	public void setMode(String mode) {
		if( params == null ){
			params = new HashMap<String,String>();
		}
		params.put("mode", mode);
	}

	public void setSender(String sender) {
		if( params == null ){
			params = new HashMap<String,String>();
		}
		params.put("sender", sender);
	}

	public void setReceivers(String receivers) {
		if( params == null ){
			params = new HashMap<String,String>();
		}
		params.put("receivers", receivers);
	}

	public void setSubject(String subject) {
		if( params == null ){
			params = new HashMap<String,String>();
		}
		params.put("subject", subject);
	}

	public void setTemplateId(String id) {
		if( params == null ){
			params = new HashMap<String,String>();
		}
		params.put("templateId", id);
	}

	public void setParams(HashMap<String, String> params) {
		if( this.params == null ){
			this.params = new HashMap<String,String>();
		}
		Iterator<String> i = params.keySet().iterator();
		while(i.hasNext()){
			String key = i.next();
			this.params.put(key, params.get(key));			
		}
	}

	public HashMap<String, String> getParams() {
		return params;
	}

	public String getSender() {
		if( params == null || !params.containsKey("sender")){
			return null;
		}
		return params.get("sender");
	}

	public String getTemplateId() {
		if( params == null || !params.containsKey("templateId")){
			return null;
		}
		return params.get("templateId");
	}

	public String getSubject() {
		if( params == null || !params.containsKey("subject")){
			return null;
		}
		return params.get("subject");
	}

	public String getReceiver() {
		if( params == null || !params.containsKey("receiver")){
			return null;
		}
		return params.get("receiver");
	}

	public void setReceiver(String receiver) {
		if( params == null ){
			params = new HashMap<String,String>();
		}
		params.put("receiver", receiver);		
	}
	
	public String getChannelData(String channelName){
		if( channelName == null || channelName.isEmpty() ){
			return null;
		}
		if( this.jsonObject != null && this.jsonObject.has(channelName.toUpperCase()) ){
			try {
				return this.jsonObject.get(channelName.toUpperCase()).toString();
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public ArrayList<Channel> getChannels() {
		ArrayList<Channel> channels = null;
		if( this.jsonObject != null && this.jsonObject.has("channels")){
			JSONArray ar = null;
			try {
				ar = (JSONArray)this.jsonObject.get("channels");
				channels = new ArrayList<Channel>();
				for(int i= 0;i<ar.length();i++){
					channels.add(Channel.getInstance(ar.get(i).toString()));
				}
				return channels;
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public ArrayList<Contact> getContacts(String channel) throws Exception{
		if(jsonObject != null && jsonObject.has(channel)){
			JSONObject j = (JSONObject)jsonObject.get(channel);
			if( j.has("contacts") ){
				JSONArray a = (JSONArray)j.get("contacts");
				ArrayList<Contact> ar = new ArrayList<Contact>();
				for(int i =0;i<a.length();i++){
					Contact c = new Contact(a.get(i).toString());
					ar.add(c);
				}
				return ar;
			}
		}
		return null;
	}
	
	public String getTemplateId(String channel) throws Exception{
		if( jsonObject != null && jsonObject.has(channel)){
			JSONObject j = (JSONObject)jsonObject.get(channel);
			if(j.has("templateId")){
				return j.getString("templateId");
			}
		}
		return null;		
	}
	
}
