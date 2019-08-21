/**
 * This is the main class to test the application if numbers are converted to English words.
 */
package com.harshini;

import com.harshini.exception.NumberConverterException;

/**
 * @author Harshini
 *
 */
public class NumberToWordCoversion {
	public static void main(String[] args) {

		WordConverter wc = new WordConverter();
		wc.setNumber(145678);
		String result;
		try {
			result = wc.convert();
			System.out.println(result);
		} catch (NumberConverterException e) {
			e.printStackTrace();
		}

	}
}
