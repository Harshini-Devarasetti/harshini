package com.harshini.test;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.harshini.WordConverter;
import com.harshini.exception.NumberConverterException;

/*
 * Test class to test WordConveter.java
 */

public class TestApp {

	WordConverter wc = new WordConverter();

	@Test
	public void testConvert(){
		//test convert method
		try {
			wc.setNumber(0);
			Assert.assertEquals("zero", wc.convert());
			wc.setNumber(999999999);
			Assert.assertEquals("nine hundred and ninety nine million nine hundred and ninety nine thousand nine hundred and ninety nine", wc.convert());
			wc.setNumber(3567);
			Assert.assertEquals("three thousand five hundred and sixty seven", wc.convert());
			wc.setNumber(3);
			Assert.assertEquals("three", wc.convert());
		} catch (NumberConverterException e) {		
			System.out.println(e.getMessage());
		}
	}

	@Rule
	public ExpectedException thrown = ExpectedException.none();
	@Test
	public void testConvertExceptionsNegativeNumbers() throws NumberConverterException{

		thrown.expect(NumberConverterException.class);
		thrown.expectMessage("Please enter positive numbers less than 999,999,999");
		wc.setNumber(-1);
		wc.convert(); 
	}

	@Test
	public void testConvertExceptionsLargeNumbers() throws NumberConverterException{

		thrown.expect(NumberConverterException.class);
		thrown.expectMessage("Please enter number less than 999,999,999");
		wc.setNumber(1999999999);
		wc.convert(); 

	}
}