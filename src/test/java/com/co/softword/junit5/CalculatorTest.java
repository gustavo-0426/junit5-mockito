package com.co.softword.junit5;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTimeout;

import java.time.Duration;
import java.util.stream.Stream;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import com.co.softworld.interfaces.ICalculator;
import com.co.softworld.junit5.Calculator;

class CalculatorTest {

	private static ICalculator calculator;

	@BeforeAll
	static void setUp() {
		calculator = new Calculator();
	}

	@AfterAll
	static void tearDown() {
		calculator = null;
	}

	@Nested
	class addTest {
		@Test
		void AddNotNullTest() {
			assertNotNull(calculator);
		}

		@Test
		void AddAssertTest() {
			assertEquals(30, calculator.add(10, 20));
		}
	}

	@Nested
	class subtractTest {
		@Test
		void subtractAssertTest() {
			assertEquals(10, calculator.subtract(10, 20));
		}
	}

	@Nested // It is used to group several test cases for a method.
	class divideTest {

		@Test
		void divideAssertTest() {
			assertEquals(5, calculator.divide(15, 3));
		}
		
		@Test
		void divideAssertDoubleRankTest() {
			assertEquals(3.3, calculator.divide(10, 3), 0.1);
		}

		@Test
		void divideAssertNegativeTest() {
			assertEquals(2.5, calculator.divide(5, 2));
		}

		@Test
		void divideAssertExceptionTest() {
			assertThrows(ArithmeticException.class, () -> calculator.divide(2, 0));
		}

		@Disabled("this annotation is used to disable test")
		@Test
		void divideAssertExceptionDisableTest() {
			assertThrows(ArithmeticException.class, () -> calculator.divide(2, 0));
		}
	}

	@ParameterizedTest(name = "{index} => num1={2}, num2={3}, sum= {5}")
	@MethodSource("addData")
	void addParameterizedTest(int num1, int num2, int sum) {
		assertEquals(sum, calculator.add(num1, num2));
	}

	static Stream<Arguments> addData() {
		return Stream.of(Arguments.of(3, 5, 8), Arguments.of(3, -2, 1), Arguments.of(-3, 2, -1),
				Arguments.of(-4, -1, -5));
	}

	@Test
	void longTaskOperationTimeOutTest() {
		assertTimeout(Duration.ofMillis(2000), () -> calculator.longTaskOperation());
	}

}