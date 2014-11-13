package com.engine.core;

import java.util.HashMap;
import java.util.Iterator;

import org.json.JSONException;
import org.json.JSONObject;

import com.engine.core.Notification.Channels;

public class Channel extends Thread{
	
	protected String name;
	HashMap<String,String> data = null;
	protected NotificationRequest request;
	protected Contact contact;
	protected Template template;
	
	protected void getChannelData(){
		if(this.request != null ){
			String jsonData = this.request.getChannelData(this.name);
			this.data = new HashMap<String,String>();
			try {
				JSONObject j = new JSONObject(jsonData);
				Iterator<String> i  = j.keys();
				while(i.hasNext()){
					String key = i.next();
					if( !"contacts".equals(key)){
						this.data.put(key, j.getString(key));	
					}
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	private String getComposedValue(String key,String value){
		Iterator<String> j = this.data.keySet().iterator();
		while(j.hasNext()){
			String skey = j.next();
			if(value.contains("%once%"+skey+"%once%") || value.contains("%all%"+skey+"%all%")){
				value = value.replace("%once%"+skey+"%once%", this.data.get(skey));
				value = value.replaceAll("%all%"+skey+"%all%", this.data.get(skey));
				return value;
			}			
		}
		return null;
	}
	
	protected void autocompose(){
		if(this.data != null && !this.data.isEmpty()){
			Iterator<String> i = this.data.keySet().iterator();
			while(i.hasNext()){
				String key = i.next();
				String value = this.data.get(key);
				if(value != null && (value.contains("%once%") || value.contains("%all%")) ){
					value = getComposedValue(key,value);
					if(value!=null){
						data.put(key, value);
					}
				}
			}
		}
	}
	
	protected boolean sendm(){
		return false;
	}
	@Override
	public void run(){
		this.sendm();
	}
	public void send(){
		this.start();
	}
	public void setTemplate(Template template) {
		this.template = template;
	}
	public void setContact(Contact contact) {
		this.contact = contact;
	}
	public void setData(HashMap<String, String> data) {
		this.data = data;
	}
	
	public static Channel getInstance(Channels channel) {
		if( Channels.EMAIL.equals(channel)){
			return new Email();
		}else if( Channels.SMS.equals(channel)){
			return new SMS();
		}else if( Channels.FACEBOOK.equals(channel)){
			return new Facebook();
		}else if( Channels.TWITTER.equals(channel)){
			return new Twitter();
		}else if( Channels.WEBHOOK.equals(channel)){
			return new WebHook();
		}else if( Channels.PUSH.equals(channel)){
			return new Push();
		}
		return null;
	}
	
	public static Channel getInstance(String channel){
		if("EMAIL".equals(channel.toUpperCase()) ){
			return new Email();
		}else if("SMS".equals(channel.toUpperCase())){
			return new SMS();
		}else if("FACEBOOK".equals(channel.toUpperCase())){
			return new Facebook();
		}else if("TWITTER".equals(channel.toUpperCase())){
			return new Twitter();
		}else if("WEBHOOK".equals(channel.toUpperCase())){
			return new WebHook();
		}
		return null;
	}
	public String getChannelName() {
		return name;
	}
	public void setRequest(NotificationRequest request) {
		this.request = request;
	}
	
}
