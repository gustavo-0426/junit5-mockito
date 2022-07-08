package com.co.softword.mockito;


import static org.junit.jupiter.api.Assertions.assertEquals;

import com.co.softworld.mockito.ValidNumber;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.co.softworld.interfaces.IValidNumber;

class ValidNumberTest {

	private static IValidNumber validaNumber;

	@BeforeAll
	static void setUp() throws Exception {
		validaNumber = new ValidNumber();
	}

	@AfterAll
	static void tearDown() throws Exception {
		validaNumber = null;
	}

	@Test
	void checkTrueNumberTest() {
		assertEquals(true, validaNumber.check(5));
	}

	@Test
	void checkFalseStringTest() {
		assertEquals(false, validaNumber.check("5"));
	}
	
	@Test
	void checkFalseNegativeTest() {
		assertEquals(false, validaNumber.check(-5));
	}
	
	@Test
	void checkFalseTenTest() {
		assertEquals(false, validaNumber.check(10));
	}
	
	@Test
	void checkFalseGreaterThanTenTest() {
		assertEquals(false, validaNumber.check(12));
	}
}