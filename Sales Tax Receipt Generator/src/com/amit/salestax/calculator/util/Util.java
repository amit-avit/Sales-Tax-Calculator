package com.amit.salestax.calculator.util;

import java.text.DecimalFormat;

public class Util {
	
	private static DecimalFormat decimalFormat = new DecimalFormat("0.00");
	
	public static double roundOffToPoint05(double decimal) {
		String numberString = String.valueOf(decimal);
		int indexOfPoint = numberString.indexOf(".");
		String major = numberString.substring(0, indexOfPoint);
		String minor = numberString.substring(indexOfPoint + 1) + "000";
		minor = minor.substring(0, 3);
		int remainder = Integer.parseInt(minor.substring(1, 3));
		if(remainder > 50) {
			minor = ( Integer.parseInt(minor.charAt(0)+"") + 1 )    + "0";
		} else if(remainder > 0) {
			minor = minor.charAt(0) + "5";
		} else {
			minor = minor.charAt(0) + "0";
		}
		numberString = major + "." + minor;
		decimal = Double.parseDouble(numberString);
		return decimal;
	}
	
	public static String formattedDecimalNumber(double decimal) {
		return decimalFormat.format(decimal);
	}

}
