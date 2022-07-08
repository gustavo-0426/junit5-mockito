package com.co.softword.mockito;

import com.co.softworld.mockito.Add;
import com.co.softworld.mockito.ValidNumber;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.co.softworld.interfaces.IAdd;
import com.co.softworld.interfaces.IValidNumber;

class AddTest {

	private static IAdd add;
	private static IValidNumber validNumber;

	@BeforeAll
	static void setUp() throws Exception {
		validNumber = Mockito.mock(ValidNumber.class);
		add = new Add(validNumber);
	}

	@AfterAll
	static void tearDown() throws Exception {
		validNumber = null;
		add = null;
	}

	@Test
	void AddMockVerifyTest() {
		add.add(3, 4);
		Mockito.verify(validNumber).check(3);
	}
}