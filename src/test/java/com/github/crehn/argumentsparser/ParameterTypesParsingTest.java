/*
 *  Copyright 2013, 2014 Christian Rehn
 *
 *  This file is part of ArgumentsParser.
 *
 *  ArgumentsParser is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU Lesser General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  ArgumentsParser is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU Lesser General Public License for more details.
 *
 *  You should have received a copy of the GNU Lesser General Public License
 *  along with ArgumentsParser.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.github.crehn.argumentsparser;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class ParameterTypesParsingTest {
	
	ArgumentParser parser;
	
	@Before
	public void setup() {
		parser = new ArgumentParser();
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void shouldThrowWhenSpecifyingParameterTwice() throws Exception {
		parser.specifyStringParameter('o');
		parser.specifyIntegerParameter('o');
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void shouldThrowWhenSpecifyingParameterTwice2() throws Exception {
		parser.specifyStringParameter('o');
		parser.specifyDoubleParameter('o');
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void shouldThrowWhenSpecifyingParameterTwice3() throws Exception {
		parser.specifyStringParameter('o');
		parser.specifyStringListParameter('o');
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void shouldThrowWhenSpecifyingTwoListParameter() throws Exception {
		parser.specifyStringListParameter('o');
		parser.specifyStringListParameter('p');
	}
	
	@Test(expected = NumberFormatException.class)
	public void shouldThrowWhenWrongParameterTypeInteger() throws Exception {
		parser.specifyIntegerParameter('p');
		
		parser.parse("-p", "not an int");
	}
	
	@Test(expected = NumberFormatException.class)
	public void shouldThrowWhenWrongParameterTypeDouble() throws Exception {
		parser.specifyDoubleParameter('p');
		
		parser.parse("-p", "not a double");
	}
	
	@Test(expected = ClassCastException.class)
	public void shouldThrowWhenWrongTypeRequested() throws Exception {
		parser.specifyIntegerParameter('p');
		parser.parse("-p", "42");
		
		@SuppressWarnings("unused")
		String d = parser.<String> getParameter('p');
	}
	
	@Test
	public void shouldReturnIntegerValueWhenSet() throws Exception {
		parser.specifyIntegerParameter('p');
		parser.parse("-p", "42");
		
		assertEquals(42, parser.getParameter('p'));
		assertEquals(Integer.valueOf(42), parser.<Integer> getParameter('p'));
	}
	
	@Test
	public void shouldReturnIntegerValueWhenSetWithLongName() throws Exception {
		parser.specifyIntegerParameter("long-parameter");
		parser.parse("--long-parameter", "42");
		
		assertEquals(42, parser.getParameter("long-parameter"));
		assertEquals(Integer.valueOf(42), parser.<Integer> getParameter("long-parameter"));
	}
	
	@Test
	public void shouldReturnDoubleValueWhenSet() throws Exception {
		parser.specifyDoubleParameter('p');
		parser.specifyDoubleParameter('q');
		parser.parse("-p", "42", "-q", "12.34");
		
		assertEquals(42.0, parser.getParameter('p'));
		assertEquals(Double.valueOf(42), parser.<Double> getParameter('p'));
		assertEquals(12.34, parser.getParameter('q'));
		assertEquals(Double.valueOf(12.34), parser.<Double> getParameter('q'));
	}
	
	@Test
	public void shouldReturnStringListValueWhenSet() throws Exception {
		parser.specifyStringListParameter('p');
		parser.parse("-p", "42", "-q", "12.34");
		
		assertEquals(asList("42", "-q", "12.34"), parser.getParameter('p'));
	}
}
