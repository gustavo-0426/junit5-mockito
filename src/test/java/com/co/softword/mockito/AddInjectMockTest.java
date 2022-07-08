package com.co.softword.mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.atMost;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import com.co.softworld.mockito.Add;
import com.co.softworld.mockito.Print;
import com.co.softworld.mockito.ValidNumber;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.ArgumentMatchers;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

public class AddInjectMockTest {

	/*
	 * Cuando un mock ejecuta un metodo, su comportamiento no es como el metodo
	 * orifinal sino que devuelve los valores establecidos por default, entero = 0,
	 * boolean = false, String = "", Object = null.
	 */
	@Mock
	private ValidNumber validNumber;
	@Mock
	private Print print;
	@InjectMocks
	private Add add;
	@Captor
	private ArgumentCaptor<Integer> captor;
	@Spy
	List<String> spyList;
	@Mock
	List<String> mockList;

	@BeforeEach
	void setUp() throws Exception {
		spyList = new ArrayList<String>();
		mockList = new ArrayList<String>();
		MockitoAnnotations.initMocks(this);
	}

	@AfterEach
	void tearDown() throws Exception {
		validNumber = null;
		print = null;
		add = null;
		captor = null;
		spyList = null;
		mockList = null;
	}

	/*
	 * Normalmente los pasos para un test son
	 * 
	 * 1. Arrange. 2. Act. 3. Assert ó 1. Given 2. When. 3. Then
	 */

	@Test
	void addPatternTest() {
		// Arrange
		when(validNumber.check(3)).thenReturn(true);
		when(validNumber.check(4)).thenReturn(true);

		// Act
		int result = add.add(3, 4);

		// Assert
		assertEquals(7, result);
	}

	// Mockito.verify
	@Test
	void addMockVerifyTest() {
		add.add(3, 4);
		verify(validNumber).check(3); // verifica que el mock ejecute el metodo check con el valor de 3 una sola vez.
//		Mockito.verify(validNumber).check(4); default value false en el paso anterior, por tanto esto no se ejecuta.
	}

	// Real Method
	@Test
	void addRealMethodTest() {
		when(validNumber.check(3)).thenCallRealMethod(); // Devuelve el valor del metodo real.
		when(validNumber.check(4)).thenCallRealMethod(); // Devuelve el valor del metodo real.
		assertEquals(7, add.add(3, 4));
	}

	// Assert Equals
	@Test
	void addWhenIsNumberValidTest() {
		when(validNumber.check(3)).thenReturn(true);
		when(validNumber.check(4)).thenReturn(true);
		assertEquals(7, add.add(3, 4));
	}

	// Assert Equals
	@Test
	void addWhenIsNumberInvalidFirtTest() {
		when(validNumber.check(-3)).thenReturn(false);
		when(validNumber.check(4)).thenReturn(true);
		assertEquals(0, add.add(-3, 4));
	}

	// Assert Equals
	@Test
	void addWhenIsNumberInvalidSecondTest() {
		when(validNumber.check(3)).thenReturn(true);
		when(validNumber.check(-4)).thenReturn(false);
		assertEquals(0, add.add(3, -4));
	}

	// Assert Equals
	@Test
	void addWhenIsNumberInvalidBothTest() {
		when(validNumber.check(-3)).thenReturn(false);
		when(validNumber.check(-4)).thenReturn(false);
		assertEquals(0, add.add(-3, -4));
	}

	// ArgumentMatcher String
	@Test
	void addPrintVerifyArgumentMatcherTest() {
		when(validNumber.check(ArgumentMatchers.anyString())).thenReturn(false);
		assertEquals(false, validNumber.check("2"));
	}

	// Mockito.verify para el metodo void addPrint()
	@Test
	void addPrintVerifyMessageTest() {
		when(validNumber.check(4)).thenReturn(true);
		when(validNumber.check(5)).thenReturn(true);
		add.addPrint(4, 5);
		verify(print).showMessage(9);
	}

	// Mockito.verify para el metodo void addPrint()
	@Test
	void addPrintVerifyMessageCaptorTest() {
		when(validNumber.check(4)).thenReturn(true);
		when(validNumber.check(5)).thenReturn(true);
		add.addPrint(4, 5);
		verify(print).showMessage(captor.capture());
		assertEquals(9, captor.getValue().intValue());
	}

	// Mockito.verify para el metodo void addPrint()
	@Test
	void addPrintVerifyErrorTest() {
		add.addPrint(4, 5);
		verify(print).showError();
	}

	// VerificationMode "times" = numero de veces que se ejecuta. Test para el
	// metodo void addPrint()
	@Test
	void addPrintVerifyTimeTest() {
		when(validNumber.check(4)).thenReturn(true);
		add.addPrint(4, 4);
		verify(validNumber, times(2)).check(4);
	}

	// VerificationMode "never" = nunca se ejecuta. Test para el metodo void
	// addPrint()
	@Test
	void addPrintVerifyNeverTest() {
		when(validNumber.check(4)).thenReturn(true);
		when(validNumber.check(5)).thenReturn(true);
		add.addPrint(4, 5);
		verify(validNumber, never()).check(6);
	}

	// VerificationMode "atLeast" = número mínimo de veces que se ejecuta. Test para
	// el metodo void addPrint()
	@Test
	void addPrintVerifyAtLeastTest() {
		when(validNumber.check(4)).thenReturn(true);
		add.addPrint(4, 4);
		verify(validNumber, atLeast(1)).check(4);
	}

	// VerificationMode "atMost" = número máximo de veces que se ejecuta. Test para
	// el metodo void addPrint()
	@Test
	void addPrintVerifyAtMostTest() {
		when(validNumber.check(4)).thenReturn(true);
		add.addPrint(4, 4);
		verify(validNumber, atMost(2)).check(4);
	}

	@Test
	void spylistTest() {
		spyList.add("1");
		spyList.add("2");
		assertEquals(2, spyList.size());
	}

	@Test
	void mocklistTest() {
		mockList.add("1");
		mockList.add("2");
		assertEquals(0, mockList.size());
	}
}