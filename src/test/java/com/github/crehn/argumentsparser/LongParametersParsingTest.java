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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;

public class LongParametersParsingTest {
	
	ArgumentParser parser;
	
	@Before
	public void setup() {
		parser = new ArgumentParser();
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void shouldThrowWhenSpecifyingParameterTwice() throws Exception {
		parser.specifyStringParameter("long-parameter");
		parser.specifyStringParameter("long-parameter");
	}
	
	@Test(expected = UnexpectedArgumentException.class)
	public void shouldThrowWhenUnexpectedArguments() throws Exception {
		parser.parse("dummy text");
	}
	
	@Test(expected = UnexpectedArgumentException.class)
	public void shouldThrowWhenTwoParameterValues() throws Exception {
		parser.specifyStringParameter("long-parameter");
		
		parser.parse("--long-parameter", "one", "two");
	}
	
	@Test(expected = UnknownArgumentException.class)
	public void whouldThrowWhenNotSpecified() throws Exception {
		parser.parse("--long-parameter", "value");
	}
	
	@Test(expected = ParameterValueMissingException.class)
	public void shouldThrowWhenArgumentMissing() throws Exception {
		parser.specifyStringParameter("long-parameter");
		parser.parse("--long-parameter");
	}
	
	@Test(expected = UnexpectedArgumentException.class)
	public void shouldThrowWhenArgumentMissing2() throws Exception {
		parser.specifyStringParameter("long-parameter");
		parser.specifyOption("other-parameter");
		parser.parse("--long-parameter", "--other-parameter", "value");
	}
	
	@Test(expected = ClassCastException.class)
	public void shouldThrowWhenCallingIsOptionSetOnAParameter() throws Exception {
		parser.specifyStringParameter("long-parameter");
		parser.parse("--long-parameter", "value");
		
		parser.isOptionSet("long-parameter");
	}
	
	@Test(expected = ParameterAlreadyOccuredException.class)
	public void shouldThrowWhenParameterGivenTwice() throws Exception {
		parser.specifyStringParameter("long-parameter");
		parser.parse("--long-parameter", "value", "--long-parameter", "value2");
	}
	
	@Test
	public void shouldReturnNullWhenParseNotCalled() throws Exception {
		parser.specifyStringParameter("long-parameter");
		
		assertNull(parser.getParameter("long-parameter"));
	}
	
	@Test
	public void shouldReturnNullWhenNotSet() throws Exception {
		parser.specifyStringParameter("long-parameter");
		parser.parse();
		
		assertNull(parser.getParameter("long-parameter"));
	}
	
	@Test
	public void shouldReturnStringValueWhenSet() throws Exception {
		parser.specifyStringParameter("long-parameter");
		parser.parse("--long-parameter", "value");
		
		assertEquals("value", parser.getParameter("long-parameter"));
	}
	
}
