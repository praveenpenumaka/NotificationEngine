package com.engine.core;

import java.util.HashMap;

public class SMS extends Channel {
	
	public SMS(){
		name = "SMS";
	}

	public boolean isValid(){
		
		if( contact == null ){
			System.out.println("ERROR: SMS: contact is null");
			return false;
		}
		
		if("".equals(contact.getPhone())){
			System.out.println("ERROR: SMS:phone number is missing");
			return false;
		}
		if( data == null  ){
			System.out.println("ERROR: Email: Data is null");
			return false;
		}
		
		if( !( data.containsKey("message")) ){
			System.out.println("ERROR: Email: Email, message or subject is null");
			return false;
		}
		
		return true;
	}

	private void getContactData() {
	}
	
	@Override
	protected boolean sendm(){
		
		getChannelData();
		
		getContactData();
		
		autocompose();

		if( !isValid()){
			System.out.println("Unable to send sms");
			return false;
		}
		System.out.println("Sending SMS");
/*
//		String message = getTemplate();
//		System.out.println("Sending SMS from "+this.from +",to "+this.to+",content:"+message);
 * */
 
		return true;
	}


	
}
