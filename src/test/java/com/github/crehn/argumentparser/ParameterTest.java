package com.github.crehn.argumentparser;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static org.junit.Assert.*;

import java.util.Collections;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class ParameterTest {
	StringParameter parameter;
	private List<String> arguments;
	
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
	public void canHandleReturnsFalseWhenDashIsMissing() throws Exception {
		assertFalse(parameter.canHandle(asList("p")));
	}
	
	@Test
	public void canHandleReturnsFalseForOtherParameter() throws Exception {
		assertFalse(parameter.canHandle(asList("-q")));
	}
	
	@Test
	public void parseHandlesEmptyList() throws Exception {
		givenArguments();
		
		List<String> yetToParse = parameter.parse(arguments);
		
		assertEquals(emptyList(), yetToParse);
		assertNull(parameter.getValue());
	}
	
	@Test
	public void parseIgnoresWhenDashMissing() throws Exception {
		givenArguments("p", "value");
		
		List<String> yetToParse = parameter.parse(arguments);
		
		assertEquals(arguments, yetToParse);
		assertNull(parameter.getValue());
	}
}
