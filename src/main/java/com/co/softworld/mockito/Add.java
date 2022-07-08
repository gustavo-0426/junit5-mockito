package com.co.softworld.mockito;

import com.co.softworld.interfaces.IAdd;
import com.co.softworld.interfaces.IPrint;
import com.co.softworld.interfaces.IValidNumber;

public class Add implements IAdd {

	private IValidNumber validNumber;
	private IPrint print;

	public Add(IValidNumber validNumber) {
		this.validNumber = validNumber;
	}
	
	public Add(IValidNumber validNumber2, IPrint print) {
		this.validNumber = validNumber2;
		this.print = print;
	}

	@Override
	public int add(Object num1, Object num2) {
		if (validNumber.check(num1) && validNumber.check(num2))
			return (Integer) num1 + (Integer) num2;
		return 0;
	}

	@Override
	public void addPrint(Object num1, Object num2) {
		if (validNumber.check(num1) && validNumber.check(num2)) {
			print.showMessage((Integer) num1 + (Integer) num2);
		} else {
			print.showError();
		}
	}
}