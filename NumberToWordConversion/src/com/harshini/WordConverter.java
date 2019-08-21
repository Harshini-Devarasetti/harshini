/**
 * This class converts numbers into English words.
 */
package com.harshini;

import java.text.DecimalFormat;
import com.harshini.exception.NumberConverterException;

public class WordConverter {

	private long number;

	public long getNumber() {
		return number;
	}

	public void setNumber(long number) {
		this.number = number;
	}

	private static final String[] tensNames = { "", " ten", " twenty", " thirty", " forty", " fifty", " sixty",
			" seventy", " eighty", " ninety" };

	private static final String[] numNames = { "", " one", " two", " three", " four", " five", " six", " seven",
			" eight", " nine", " ten", " eleven", " twelve", " thirteen", " fourteen", " fifteen", " sixteen",
			" seventeen", " eighteen", " nineteen" };

	/*
	 * This method converts numbers less than thousand to english words.
	 */
	private static String convertLessThanOneThousand(int number) {
		String soFar;

		if (number % 100 < 20) {
			soFar = numNames[number % 100];
			number /= 100;
		} else {
			soFar = numNames[number % 10];
			number /= 10;

			soFar = tensNames[number % 10] + soFar;
			number /= 10;
		}
		if (number == 0)
			return soFar;
		if (soFar.length() == 0) {
			return numNames[number] + " hundred";
		}
		return numNames[number] + " hundred and " + soFar;
	}

	/*
	 * This method converts the milion part of number into english words.
	 */
	private String getMillions(int millions) {
		String tradMillions;
		switch (millions) {
		case 0:
			tradMillions = "";
			break;
		case 1:
			tradMillions = convertLessThanOneThousand(millions) + " million ";
			break;
		default:
			tradMillions = convertLessThanOneThousand(millions) + " million ";
		}
		return tradMillions;
	}

	/*
	 * This method converts houndred thousand part of number into english words.
	 */
	private String getHundredThousands(int hundredThousands) {
		String tradHundredThousands;
		switch (hundredThousands) {
		case 0:
			tradHundredThousands = "";
			break;
		case 1:
			tradHundredThousands = "one thousand ";
			break;
		default:
			tradHundredThousands = convertLessThanOneThousand(hundredThousands) + " thousand ";
		}
		return tradHundredThousands;
	}

	/*
	 * This method divides the number into million, hundred thousand and thousand
	 * part and converts into english words.
	 */
	public String convert() throws NumberConverterException {

		if (this.number == 0) {
			return "zero";
		}

		if (this.number > 999999999) {
			throw new NumberConverterException("Please enter number less than 999,999,999");
		}

		if (this.number < 0) {
			throw new NumberConverterException("Please enter positive numbers less than 999,999,999");
		}

		String snumber = Long.toString(this.number);

		// pad with "0"
		String mask = "000000000";
		DecimalFormat df = new DecimalFormat(mask);
		snumber = df.format(this.number);

		// XXXnnnnnn --> million part of number
		int millions = Integer.parseInt(snumber.substring(0, 3));
		// nnnXXXnnn --> hundred thousand part of number
		int hundredThousands = Integer.parseInt(snumber.substring(3, 6));
		// nnnnnnXXX --> thousands part of number
		int thousands = Integer.parseInt(snumber.substring(6, 9));

		String result = getMillions(millions);

		result = result + getHundredThousands(hundredThousands);

		result = result + convertLessThanOneThousand(thousands);

		// remove extra spaces
		return result.replaceAll("^\\s+", "").replaceAll("\\b\\s{2,}\\b", " ");
	}

}
