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

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class OptionsParsingTest {
	
	ArgumentParser parser;
	
	@Before
	public void setup() {
		parser = new ArgumentParser();
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void shouldThrowWhenSpecifyingOptionTwice() throws Exception {
		parser.specifyOption('o');
		parser.specifyOption('o');
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void shouldThrowWhenAlreadyAParameter() throws Exception {
		parser.specifyStringParameter('o');
		parser.specifyOption('o');
	}
	
	@Test(expected = UnknownArgumentException.class)
	public void shouldThrowWhenUnspecifiedOption() throws Exception {
		parser.parse("-o");
	}
	
	@Test(expected = UnknownArgumentException.class)
	public void shouldThrowWhenAFurtherUnspecifiedOption() throws Exception {
		parser.specifyOption('o');
		
		parser.parse("-o", "-u");
	}
	
	@Test(expected = UnknownArgumentException.class)
	public void shouldThrowWhenAFurtherUnspecifiedConcatenatedOption() throws Exception {
		parser.specifyOption('o');
		
		parser.parse("-ou");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void shouldThrowWhenOptionNotSpecified() throws Exception {
		parser.parse();
		
		assertFalse(parser.isOptionSet('o'));
	}
	
	@Test
	public void shouldReturnFalseWhenParseNotCalled() throws Exception {
		parser.specifyOption('o');
		
		assertFalse(parser.isOptionSet('o'));
	}
	
	@Test
	public void shouldReturnFalseForEmptyArgs() throws Exception {
		parser.specifyOption('o');
		parser.parse();
		
		assertFalse(parser.isOptionSet('o'));
	}
	
	@Test
	public void shouldReturnTrueWhenOptionSet() throws Exception {
		parser.specifyOption('o');
		parser.parse("-o");
		
		assertTrue(parser.isOptionSet('o'));
	}
	
	@Test
	public void shouldReturnFalseWhenOptionNotSet() throws Exception {
		parser.specifyOption('o');
		parser.specifyOption('u');
		parser.parse("-o");
		
		assertFalse(parser.isOptionSet('u'));
	}
	
	@Test
	public void shouldSetReturnTrueWhenSecondOptionSet() throws Exception {
		parser.specifyOption('o');
		parser.specifyOption('u');
		parser.parse("-o", "-u");
		
		assertTrue(parser.isOptionSet('o'));
		assertTrue(parser.isOptionSet('u'));
	}
	
	@Test
	public void shouldReturnTrueWhenConcatenatedOptionSet() throws Exception {
		parser.specifyOption('o');
		parser.specifyOption('u');
		parser.parse("-ou");
		
		assertTrue(parser.isOptionSet('o'));
		assertTrue(parser.isOptionSet('u'));
	}
	
	@Test
	public void shouldReturnTrueWhenLongFormIsSet() throws Exception {
		parser.specifyOption("long-option", 'o');
		parser.parse("--long-option");
		
		assertTrue(parser.isOptionSet('o'));
	}
}
