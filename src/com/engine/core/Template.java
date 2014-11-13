package com.engine.core;

import java.io.*;
import java.util.*;



public class Template {

	private String templateId;

	private String rawTemplateData;
	
	private HashMap<String,String> params;
	
	public Template(String templateId) throws Exception{
		ROOT_TEMPLATE_DIRECTORY = "C:\\Users\\sunil\\Documents\\workspace\\n";
		this.templateId = templateId;
		this.params = new HashMap<String,String>();
		readTemplate();
		parseTemplate();
	}
	
	private void readTemplate() throws IOException{

		String templateFilePath = ROOT_TEMPLATE_DIRECTORY + "\\" + templateId + ".template";

		File templateFile = new File(templateFilePath);
		
		Reader fr = new FileReader(templateFile);
		
		BufferedReader br = new BufferedReader(fr);
			
		String line = "";
		StringBuffer content = new StringBuffer(line);
		while((line = br.readLine())!= null){
			content.append(line);
		}
		
		rawTemplateData = content.toString();
		
	}
	
	private void parseTemplate() throws Exception{
		if( rawTemplateData == null || rawTemplateData.isEmpty() ){
			throw new Exception();
		}
		String[] lines = rawTemplateData.split("\n");
		for(int i=0;i<lines.length;i++){
			String[] keyval = lines[i].split(":");
			String key = keyval[0].trim();
			String value = keyval[1].trim();
			params.put(key,value);
		}
		
		//check mandatory params
		if( !(params.containsKey("modes") 
				&& params.containsKey("message")
				&& params.containsKey("from")) ){
			this.clear();
			throw new Exception();
		}
		
	}
	
	public HashMap<String,String> getParams(){
		return this.params;
	}
	

	public boolean setParams(HashMap<String,String> data){
		if( data == null || data.isEmpty() ){
			return false;
		}
		if(params == null || params.isEmpty()){
			return false;
		}
		Iterator<String> i = data.keySet().iterator();
		while(i.hasNext()){
			String key = i.next();
			String value = data.get(key);
			if( params.containsKey(key)){
				System.out.println("Replacing "+key+" value with :"+value);
			}
			params.put(key, value);
		}
		return true;
	}
	
	private void autocompose(){
		
	}
	
/*	public Template compose(String key,Contact contact){
		
		if( key == null || key.isEmpty() ){
			return null;
		}
		if( params != null && !params.containsKey(key) ){
			return null;
		}
		if( contact== null ){
			return null;
		}
		
	}
*/
	private void clear(){
		params.clear();
		rawTemplateData = "";
	}
	
	private Notification getInstance(String mode,String csl){
		Notification n = null;
		if( params.get("modes").contains(mode)){
			//n = Notification.getInstance(mode);
			//n.setParams(params);
			//n.setReceivers(Receiver.getInstance(csl));
		}
		return n;
	}
	
	public void sendAll(){
		
		if( params == null || params.isEmpty() || !params.containsKey("modes")){
			return;
		}
				
		String[] modes = params.get("models").split(",");
		
		for(int i=0;i<modes.length;i++){
			//TODO:this.getInstance(modes[i].trim()).sendMessage();
		}
		
	}
	
	// DIRECTORY for templates
	public static String ROOT_TEMPLATE_DIRECTORY;
	
	public static String EMAIL_TEMPLATE_DIRECTORY = "/email";
	
	public static String SMS_TEMPLATE_DIRECTORY = "/sms";
		
	public static String MESSAGE_TEMPLATE_DIRECTORY = "/messages";
	
	
	public String format(String content){
		return content;
	}
	
	public String getMessage(HashMap<String,String> data,String mode) throws IOException{
		String templateContent = readTemplate(templateId,mode);
		if(!("".equals(templateContent) &&templateContent.isEmpty()) ){
			//parseTemplate(templateContent);
			//String message = populateData(data,templateContent);
			//return format(message);
			return "";
		}else{
			return "";
		}
	}
	
	
	public String readTemplate(String templateId, String mode) throws IOException{
		
		String templateFilePath = ROOT_TEMPLATE_DIRECTORY + "\\" + mode +"\\"+ templateId + ".template";

		File templateFile = new File(templateFilePath);
		
		if(templateFile.exists()){

			Reader fr = new FileReader(templateFile);
		
			BufferedReader br = new BufferedReader(fr);
			
			String line = "";
			StringBuffer content = new StringBuffer(line);
			while((line = br.readLine())!= null){
				content.append(line);
			}
			
			return content.toString();
			
		}
		
		return "";
		
	}

	
	
	

}
