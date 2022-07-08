package com.co.softworld.mockito;

import com.co.softworld.interfaces.IValidNumber;

public class ValidNumber implements IValidNumber {

	@Override
	public boolean check(Object object) {
		if (object instanceof Integer) {
			if ((Integer) object >= 0 && (Integer) object < 10)
				return true;
		}
		return false;
	}

}
