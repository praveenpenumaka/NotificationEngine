package com.engine.queue;

import com.engine.core.Notification;
import com.engine.core.NotificationRequest;

public class NotificationRequestHandler implements Runnable{

	@Override
	public void run() {
		while(true){
			if(NotificationRequestQueue.peek() != null){
				NotificationRequest nr = NotificationRequestQueue.pop();
				if(nr.isValid()){
					//Notification n = Notification.getInstance(nr.getMode());
					//n.setParams(nr);
					//n.sendMessage();
				}
			}
		}
	}
	
}
