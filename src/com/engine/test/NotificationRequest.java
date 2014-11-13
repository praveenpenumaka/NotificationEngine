package com.engine.test;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

public class NotificationRequest {
	
	public com.engine.core.NotificationRequest nr;

	@Before
	public void setUp() throws Exception {
		nr = new com.engine.core.NotificationRequest();
	}	

	@Test
	public void test_creator() {
		assertNotNull(nr);
	}
	
	@Test
	public void test_isvalid(){
		assertFalse(nr.isValid());
		nr.setMode("EMAIL");
		assertTrue(nr.isValid());
	}
	
	@Test
	public void test_setget_mode(){
		nr.setMode("TEST_MODE");
		assertTrue("TEST_MODE".equals(nr.getMode()));
	}

	@Test
	public void test_null_mode(){
		assertTrue(nr.getMode() == null );
	}

	@Test
	public void test_setget_sender(){
		nr.setSender("TEST_SENDER");
		assertTrue("TEST_SENDER".equals(nr.getSender()));
	}
	
	@Test
	public void test_null_sender(){
		assertTrue(nr.getSender() == null );
	}
	
	@Test
	public void test_setget_receivers(){
		nr.setReceiver("TEST_RECEIVER");
		assertTrue("TEST_RECEIVER".equals(nr.getReceiver()));
	}

	@Test
	public void test_null_receivers(){
		assertTrue(nr.getReceiver() == null );
	}
	
	@Test
	public void test_setget_subect(){
		nr.setSubject("TEST_SUBJECT");
		assertTrue("TEST_SUBJECT".equals(nr.getSubject()));
	}

	@Test
	public void test_null_subect(){
		assertTrue(nr.getSubject() == null );
	}
	@Test
	public void test_getset_templateid(){
		nr.setTemplateId("TEST_TEMPLATE_ID");
		assertTrue("TEST_TEMPLATE_ID".equals(nr.getTemplateId()));
	}
	@Test
	public void test_null_templateid(){
		assertTrue(nr.getTemplateId() == null );
	}
	@Test
	public void test_setget_params(){
		HashMap<String,String> params = new HashMap<String,String>();
		nr.setParams(params);
		assertTrue(params.equals(nr.getParams()));
	}
	@Test
	public void test_null_params(){
		assertTrue(nr.getParams() == null );
	}
}
