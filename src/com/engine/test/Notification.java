package com.engine.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class Notification {

	com.engine.core.NotificationRequest nr = null;

	com.engine.core.Notification gn = null;
	
	@Before
	public void Setup(){
		nr = new com.engine.core.NotificationRequest();
		try {
			nr.initializeFromJson("{}");
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		nr.setMode("EMAIL");
		try {
			gn = new com.engine.core.Notification(nr);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void test_init() {
		boolean exception =false;
		try {
			com.engine.core.Notification n = new com.engine.core.Notification(null);
		} catch (Exception e) {
			exception = true;
		}
		assertTrue(exception);
		exception = false;
		com.engine.core.Notification n = null;
		try {
			n = new com.engine.core.Notification(nr);
		} catch (Exception e) {
			exception = true;
		}
		assertFalse(exception);
		assertNotNull(n);
	}
	
	@Test
	public void test_gettemplate(){
		fail("");
	}

}
