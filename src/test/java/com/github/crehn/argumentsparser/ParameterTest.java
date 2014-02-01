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
import static org.junit.Assert.*;

import java.util.Collections;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class ParameterTest {
	
	StringParameter parameter;
	List<String> arguments;
	
	@Before
	public void setup() {
		parameter = new StringParameter('p');
	}
	
	private void givenArguments(String... arguments) {
		this.arguments = asList(arguments);
	}
	
	@Test
	public void canHandleReturnsFalseForEmptyList() throws Exception {
		assertFalse(parameter.canHandle(Collections.<String> emptyList()));
	}
	
	@Test
	public void parseReturnsIdentityWhenCannotHanlde() throws Exception {
		givenArguments("-q", "value");
		
		List<String> yetToParse = parameter.parse(arguments);
		
		assertEquals(asList("-q", "value"), yetToParse);
		assertNull(parameter.getValue());
	}
}
