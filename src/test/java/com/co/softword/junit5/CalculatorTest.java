package com.co.softword.junit5;

import static java.time.Duration.ofMillis;
import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTimeout;

import java.util.stream.Stream;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

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

    @Nested
    @Tag("divide")
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

    @Tag("add")
    @ParameterizedTest
    @ValueSource(ints = {2, 4, 6})
    void addValueSourceParameterizedTest(int num1) {
        int sum = 8;
        int num2 = sum - num1;
        assertEquals(sum, calculator.add(num1, num2));
    }

    @Tag("add")
    @ParameterizedTest
    @CsvSource({"3,5,8", "3,-2,1", "-3,2,-1", "-4,-1,-5"})
    void addCsvSourceParameterizedTest (int num1, int num2, int sum) {
        assertEquals(sum, calculator.add(num1, num2));
    }

    @Tag("add")
    @ParameterizedTest
    @CsvSource(value = {"3:5:8", "3:-2:1", "-3:2:-1", "-4:-1:-5"}, delimiter = ':')
    void addCsvSourceDelimiterParameterizedTest (int num1, int num2, int sum) {
        assertEquals(sum, calculator.add(num1, num2));
    }

    @Tag("add")
    @ParameterizedTest
    @CsvFileSource(resources = {"/CsvFileTest.csv"})
    void addCsvDelimiterParameterizedTest (int num1, int num2, int sum) {
        assertEquals(sum, calculator.add(num1, num2));
    }

    @Tag("add")
    @ParameterizedTest()
    @MethodSource("addData")
    void addMethodSourceParameterizedTest(int num1, int num2, int sum) {
        assertEquals(sum, calculator.add(num1, num2));
    }

    static Stream<Arguments> addData() {
        return Stream.of(Arguments.of(3, 5, 8), Arguments.of(3, -2, 1), Arguments.of(-3, 2, -1),
                Arguments.of(-4, -1, -5));
    }

    @Test
    void longTaskOperationTimeOutTest() {
        assertTimeout(ofMillis(2000), () -> calculator.longTaskOperation());
    }

    @Test
    @Timeout(2)
    void longTaskOperationTimeOutAnnotationTest() {
        calculator.longTaskOperation();
    }

    @Test
    @Timeout(value = 2000, unit = MILLISECONDS)
    void longTaskOperationTimeOutAnnotationCustomTest() {
        calculator.longTaskOperation();
    }

    @Test
    @Tag("divide")
    void argumentCaptorTest() {
        assertEquals(3, calculator.divide(15, 5));
    }

    @Test
    @Tag("divide")
    void tagDivideTest() {
        assertEquals(10, calculator.divide(20, 2));
    }

    @Test
    @Tag("divide")
    void tagDivide2Test() {
        assertEquals(12, calculator.divide(48, 4));
    }

    @Test
    @Tag("add")
    void tagAddTest() {
        assertEquals(8, calculator.add(5, 3));
    }

}