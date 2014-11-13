package com.engine.core;

public class Facebook extends Channel {

	public Facebook(){
		name= "FACEBOOK";
	}
	
	private boolean isValid(){
		
		if(contact.getPageId() == null || "".equals(contact.getPageId())){
			return false;
		}
/*
		message
		link
		picture
		name
		caption
		description
		actions
		place
		tags
		object_attachment
		targeting
		feed_targeting
		published
		scheduled_publish_time
		backdated_time
		backdated_time_granularity
*/
		return true;
	}
	
	@Override
	protected boolean sendm(){
		return false;		
	}

}
