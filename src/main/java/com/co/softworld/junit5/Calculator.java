package com.co.softworld.junit5;

import com.co.softworld.interfaces.ICalculadora;

public class Calculadora implements ICalculadora {

	@Override
	public int add(int num1, int num2) {
		return num1 + num2;
	}

	@Override
	public int subtract(int num1, int num2) {
		return num2 - num1;
	}

	@Override
	public double divide(double num1, double num2) {
		if (num2 == 0)
			throw new ArithmeticException("Division por cero no está definida");
		return num1 / num2;
	}

	@Override
	public void longTaskOperation() {
		try {
			Thread.sleep(1000);
		} catch (Exception e) {
		}
	}

}
