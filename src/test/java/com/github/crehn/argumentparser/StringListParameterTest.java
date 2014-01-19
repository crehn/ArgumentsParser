package com.github.crehn.argumentparser;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.github.crehn.argumentparser.StringListParameter;

public class StringListParameterTest {
	StringListParameter parameter;
	private List<String> arguments;
	
	@Before
	public void setup() {
		parameter = new StringListParameter('p');
	}
	
	private void givenArguments(String... arguments) {
		this.arguments = asList(arguments);
	}
	
	@Test
	public void parseEmptyList() throws Exception {
		givenArguments("-p");
		
		List<String> yetToParse = parameter.parse(arguments);
		
		assertEquals(emptyList(), yetToParse);
		assertEquals(emptyList(), parameter.getValue());
	}
	
	@Test
	public void parseOneString() throws Exception {
		givenArguments("-p", "one");
		
		List<String> yetToParse = parameter.parse(arguments);
		
		assertEquals(emptyList(), yetToParse);
		assertEquals(asList("one"), parameter.getValue());
	}
	
	@Test
	public void parseTwoStrings() throws Exception {
		givenArguments("-p", "one", "two");
		
		List<String> yetToParse = parameter.parse(arguments);
		
		assertEquals(emptyList(), yetToParse);
		assertEquals(asList("one", "two"), parameter.getValue());
	}
}
