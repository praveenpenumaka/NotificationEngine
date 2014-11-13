package com.engine.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class Template {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test_create_template() {
		try {
			com.engine.core.Template t = new com.engine.core.Template("345");
		} catch (Exception e) {
			fail("Unable to create template");
		}
		fail("Not yet implemented");
	}
	

}
