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

import org.junit.Before;
import org.junit.Test;

public class ArgumentParserTest {
	
	ArgumentParser parser;
	
	@Before
	public void setup() {
		parser = new ArgumentParser();
	}
	
	@Test(expected = UnexpectedArgumentException.class)
	public void parseThrowsWhenOnlyEmptyString() throws Exception {
		parser.parse("");
	}
	
	@Test(expected = UnexpectedArgumentException.class)
	public void parseThrowsWhenOnlyDash() throws Exception {
		parser.specifyOption("long-option", 'o');
		
		parser.parse("-");
	}
}