package com.co.softword.junit5;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTimeout;

import java.time.Duration;
import java.util.stream.Stream;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import com.co.softworld.interfaces.ICalculadora;
import com.co.softworld.junit5.Calculadora;

class CalculadoraTest {

	private static ICalculadora calculadora;

	@BeforeAll
	static void setUp() throws Exception {
		calculadora = new Calculadora();
	}

	@AfterAll
	static void tearDown() throws Exception {
		calculadora = null;
	}

	@Nested
	class addTest {
		@Test
		void AddNotNullTest() {
			assertNotNull(calculadora);
		}

		@Test
		void AddAsserTest() {
			assertEquals(30, calculadora.add(10, 20));
		}
	}

	@Nested
	class subtractTest {
		@Test
		void subtractAsertTest() {
			assertEquals(10, calculadora.subtract(10, 20));
		}
	}

	@Nested // sirve para agrupar varios casos de test para un metodo.
	class divideTest {

		@Test
		void divideAsertTest() {
			assertEquals(5, calculadora.divide(15, 3));
		}
		
		@Test
		void divideAsertDoubleRankTest() {
			assertEquals(3.3, calculadora.divide(10, 3), 0.1);
		}

		@Test
		void divideAsertNegativeTest() {
			assertEquals(2.5, calculadora.divide(5, 2));
		}

		@Test
		void divideAsertExceptionTest() {
			assertThrows(ArithmeticException.class, () -> calculadora.divide(2, 0));
		}

		@Disabled("Este test se ha desabilitado de forma temporal")
		@Test
		void divideAsertExceptionDisableTest() {
			assertThrows(ArithmeticException.class, () -> calculadora.divide(2, 0));
		}
	}

	@ParameterizedTest(name = "{index} => num1={2}, num2={3}, suma= {5}")
	@MethodSource("addData")
	void addParameterizedTest(int num1, int num2, int suma) {
		assertEquals(suma, calculadora.add(num1, num2));
	}

	static Stream<Arguments> addData() {
		return Stream.of(Arguments.of(3, 5, 8), Arguments.of(3, -2, 1), Arguments.of(-3, 2, -1),
				Arguments.of(-4, -1, -5));
	}

	@Test
	void longTaskOperationTimeOutTest() {
		assertTimeout(Duration.ofMillis(2000), () -> calculadora.longTaskOperation());
	}

}