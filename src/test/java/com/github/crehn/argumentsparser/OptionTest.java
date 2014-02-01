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
import static java.util.Collections.emptyList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class OptionTest {
	
	Option option;
	
	@Before
	public void setup() {
		option = new Option('o');
	}
	
	@Test
	public void parseSetsFalseWhenEmptyString() throws Exception {
		option.parse(asList(""));
		
		assertFalse(option.getIsSet());
	}
	
	@Test
	public void parseSetsFalseWhenOtherOption() throws Exception {
		List<String> yetToParse = option.parse(asList("-u"));
		
		assertFalse(option.getIsSet());
		assertEquals(asList("-u"), yetToParse);
	}
	
	@Test
	public void parseSetsFalseWhenMissingDash() throws Exception {
		List<String> yetToParse = option.parse(asList("o"));
		
		assertFalse(option.getIsSet());
		assertEquals(emptyList(), yetToParse);
	}
}
