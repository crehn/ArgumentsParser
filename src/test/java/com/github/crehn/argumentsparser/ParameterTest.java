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
