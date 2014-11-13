package com.engine.queue;

import java.util.LinkedList;
import java.util.Queue;

import com.engine.core.NotificationRequest;

public class NotificationRequestQueue{
	
	public static Queue<NotificationRequest> queue = new LinkedList();
	
	public static void push(NotificationRequest n){
		queue.offer(n);
	}
	
	public static NotificationRequest peek(){
		return queue.peek();
	}
	
	public static NotificationRequest pop(){
		return queue.poll();
	}
	
}
