package com.github.crehn.argumentparser;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.github.crehn.argumentparser.StringParameter;

public class StringParameterTest {
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
	public void parseStringValue() throws Exception {
		givenArguments("-p", "value");
		
		List<String> yetToParse = parameter.parse(arguments);
		
		assertEquals(emptyList(), yetToParse);
		assertEquals("value", parameter.getValue());
	}
}
