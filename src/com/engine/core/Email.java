package com.engine.core;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.HashMap;


public class Email extends Channel {
	
	/*
	public static void sendMessage(Gmail service, String userId, MimeMessage email)
		      throws MessagingException, IOException {
		    Message message = createMessageWithEmail(email);
		    message = service.users().messages().send(userId, message).execute();

		    System.out.println("Message id: " + message.getId());
		    System.out.println(message.toPrettyString());
	}
	
	public static Message createMessageWithEmail(MimeMessage email)
		      throws MessagingException, IOException {
		    ByteArrayOutputStream baos = new ByteArrayOutputStream();
		    email.writeTo(baos);
		    String encodedEmail = Base64.encodeBase64URLSafeString(baos.toByteArray());
		    Message message = new Message();
		    message.setRaw(encodedEmail);
		    return message;
	}
	*/
	
	public Email(Template template,Contact contact, HashMap<String,String> params){
		name = "EMAIL";
	}
	
	public Email() {
		name = "EMAIL";
	}

	public void sendEmail(){
        try
        {
        	Properties props = System.getProperties();
        	if( this.data.containsKey("smtp")){
        		props.put("mail.smtp.host", this.data.get("smtp"));
        	}else{
        		props.put("mail.smtp.host", "");
        	}
			Session session = Session.getInstance(props, null);  

			MimeMessage msg = new MimeMessage(session);
			//set message headers
			if( this.data.containsKey("content-type") ){
				msg.addHeader("Content-type", this.data.get("content-type"));  
			}else{
				msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
			}
          
			if( this.data.containsKey("format") ){
				msg.addHeader("format", this.data.get("format"));
			}else{
				msg.addHeader("format", "flowed");
			}
          
			if( data.containsKey("content-transfer-encoding")){
				msg.addHeader("Content-Transfer-Encoding", data.get("content_transfer_encoding"));
			}else{
				msg.addHeader("Content-Transfer-Encoding", "8bit");
			}
          
			// MANDATORY
			msg.setFrom(new InternetAddress(data.get("from"), "NoReply-JD"));
 
			if( data.containsKey("replyto")){
				msg.setReplyTo(InternetAddress.parse(data.get("replyto"), true));
			}else{
				msg.setReplyTo(InternetAddress.parse("no_reply@locahost", false));        	  
			}
 
			String encoding = "UTF-8";
			if( data.containsKey("subject-encoding")){
				encoding = data.get("subject-encoding");
			}
			msg.setSubject(data.get("subject"), encoding);
 
			encoding = "UTF-8";
			if( data.containsKey("body_encoding")){
				encoding = data.get("data_encoding");
			}
			msg.setText(data.get("body"), encoding);
 
			Date date = new Date();
			if( data.containsKey("sent_date")){
				date = new Date(data.get("sent_date"));
			}
			msg.setSentDate(date);
 
			msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(contact.getEmail(), false));
			System.out.println("Message is ready");
			Transport.send(msg);  
 
			System.out.println("EMail Sent Successfully!!");
        }catch (Exception e) {
        	e.printStackTrace();
        }
    }
	
	private void email_gateway_smtp(){
		
	}

	private void email_gateway_gmail(){
		
	}

	public boolean isValid(){
		
		if( contact == null ){
			return false;
		}
		if( contact.getEmail() == null || "".equals(contact.getEmail()) || !contact.getEmail().contains("@") || !contact.getEmail().contains(".")){
			System.out.println("ERROR: Email: contact error");
			return false;
		}
		if( data == null  ){
			System.out.println("ERROR: Email: Data is null");
			return false;
		}
		
		if( !( data.containsKey("message") && data.containsKey("subject") && data.containsKey("from")) ){
			System.out.println("ERROR: Email: Email, from, message or subject is null");
			return false;
		}
		
		return true;
	}
	
	private void getContactData(){
		this.data.put("email", contact.getEmail());
		this.data.put("full_name", contact.getFull_name());
		this.data.put("first_name", contact.getFirst_name());
		this.data.put("last_name", contact.getLast_name());
		this.data.put("short_name", contact.getShort_name());
	}
	
	@Override
	protected boolean sendm(){
		
		getChannelData();
		
		getContactData();
		
		autocompose();
		
		if(!isValid()){
			System.out.println("Unable to send email");
			return false;
		}
		
		System.out.println("Sending mail to "+contact.getEmail()+",message:"+data.get("message"));
/*
		String message = data.get("emailmessage");
		if( !("".equals(message)) ){
			System.out.println("Sending email from "+data.get("from") +",to "+data.get("to")+",content:"+message);
		}else{
			System.out.println("Cannot send empty message");
			return false;			
		}
		return true;
	}
*/
		return true;
	}

}
