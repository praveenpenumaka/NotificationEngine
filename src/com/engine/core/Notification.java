package com.engine.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;


public class Notification extends Thread{
	
	public static enum Channels{
		SMS,
		EMAIL,
		WEBHOOK,
		FACEBOOK,
		TWITTER,
		PUSH
	};

	private NotificationRequest request;

	private Template template;
	
	protected HashMap<String,String> data;

	protected Contact receiver;
		
	protected String templateId;
	
	protected String mode;

	
	public Notification(NotificationRequest nr) throws Exception {
		if( nr == null || !nr.isValid()){
			throw new Exception();
		}
		this.request = nr;
//		data.put("mode", nr.getMode());
	}

	public boolean setParams(HashMap<String,String> params){
		if( params == null ){
			return false;
		}
		if( data == null ){
			data = new HashMap<String,String>();
		}
		Iterator<String> i = params.keySet().iterator();
		while(i.hasNext()){
			String key = i.next();
			data.put(key, params.get(key));
		}
		return true;
	}
	
	private ArrayList<Contact> getContacts(Channel channel){
		try {
			return this.request.getContacts(channel.getChannelName());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public void init(){

	}
	
	@Override
	public void run(){

		init();

		ArrayList<Channel> channels = getChannels();
		Iterator<Channel> channelIterator = channels.iterator();
		while(channelIterator.hasNext()){
			Channel sendingChannel = channelIterator.next();
			ArrayList<Contact> contacts = getContacts(sendingChannel);
			if( contacts != null ){
				Template channelTemplate = getTemplate(sendingChannel);
				Iterator<Contact> contactIterator = contacts.iterator();
				while(contactIterator.hasNext()){
						Contact contact = contactIterator.next();
						sendingChannel.setTemplate(channelTemplate);
						sendingChannel.setContact(contact);
						sendingChannel.setRequest(request);
						sendingChannel.send();
				}
			}
		}
	}
	
	private ArrayList<Channel> getChannels() {		
		return this.request.getChannels();
	}

	// TODO
	private void compose(String key,String value){
		data.put(key, data.get(key).replace("", ""));
		data.put(key, data.get(key).replaceAll("", ""));
	}
	
	// notification level composing
	private void autocompose() {
		if( data == null || data.isEmpty() ){
			return;
		}
		
		Iterator<String> i = data.keySet().iterator();
		while(i.hasNext()){
			String key = i.next();
			compose(key, data.get(key));
		}
	}

	public void sendMessage(){
		this.start();
	}
	
	/* Template and request data population */
	public Template getTemplate(Channel channel){
		// this is not mandatory at this point
		if( data == null || data.isEmpty() || !data.containsKey("templateId") ){
			return null;
		}
		try {
			String templateId = request.getTemplateId(channel.getChannelName());
			if( templateId != null && !templateId.isEmpty()){
				Template t = new Template(templateId);
				return t;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private void getTemplateData() {
		if( this.template == null ){
			return;
		}
		
		HashMap<String,String> par = template.getParams();
		setParams(par);
	
		return;
	}
	
	public void getRequestData(){
		if( request == null || !request.isValid()){
			return;
		}
		
		HashMap<String,String> par = request.getParams();
		setParams(par);
		
		return;
	}

	

	/*
	public static Notification getInstance(channels mode) {
		if(channels.EMAIL.equals(mode)){
			return new Email();
		}else if(channels.SMS.equals(mode)){
			return new SMS();
		}else if(channels.WEBHOOK.equals(mode)){
			return new WebHook();
		}
		return null;
	}
	*/
	public void setReceivers(Contact receiver) {
		this.receiver = receiver;
	}


	
}
