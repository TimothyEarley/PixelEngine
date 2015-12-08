package de.earley.pixelengine.util;

import java.text.DecimalFormat;

public abstract class StringUtil {

	public static String toDecimal(double number, int places, boolean zeros) {
		Character c = (zeros) ? '0' : '#';
		StringBuilder decimalsBuilder = new StringBuilder();
		for (int i = 0; i < places; i++) {
			decimalsBuilder.append(c);
		}
		return new DecimalFormat("#0." + decimalsBuilder).format(number);
	}

}
