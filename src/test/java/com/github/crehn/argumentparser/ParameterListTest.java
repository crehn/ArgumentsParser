package com.github.crehn.argumentparser;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.Collections;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.github.crehn.argumentparser.*;

public class ParameterListTest {
	ArgumentList<String> parameterList;
	private List<String> arguments;
	
	@Before
	public void setup() {
		parameterList = new ArgumentList<>();
	}
	
	private void givenArguments(String... arguments) {
		this.arguments = asList(arguments);
	}
	
	private void givenParameters(char... parameters) {
		for (char p : parameters)
			parameterList.add(new StringParameter(p));
	}
	
	@Test
	public void parseHandlesEmptyList() throws Exception {
		List<String> yetToParse = parameterList.parse(Collections.<String> emptyList());
		
		assertEquals(emptyList(), yetToParse);
	}
	
	@Test(expected = NullPointerException.class)
	public void parseHandlesNull() throws Exception {
		parameterList.parse(null);
	}
	
	@Test
	public void parseReturnsIdentityWhenNoParameterSpecified() throws Exception {
		givenArguments("-p");
		List<String> yetToParse = parameterList.parse(arguments);
		
		assertEquals(arguments, yetToParse);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void getByNameThrowsWhenParameterNotSpecified() throws Exception {
		givenArguments("-p");
		parameterList.parse(arguments);
		
		parameterList.getValueByName('p');
	}
	
	@Test
	public void parseReturnsIdentityWhenOtherParameterSpecified() throws Exception {
		givenParameters('p');
		givenArguments("-q");
		List<String> yetToParse = parameterList.parse(arguments);
		
		assertEquals(arguments, yetToParse);
		assertNull(parameterList.getValueByName('p'));
	}
	
	@Test(expected = ParameterValueMissingException.class)
	public void parseThrowsWhenParameterValueMissing() throws Exception {
		givenParameters('p');
		givenArguments("-p");
		
		parameterList.parse(arguments);
	}
	
	@Test
	public void parseHandlesSingleSpecifiedParameter() throws Exception {
		givenParameters('p');
		givenArguments("-p", "value");
		List<String> yetToParse = parameterList.parse(arguments);
		
		assertEquals(emptyList(), yetToParse);
		assertEquals("value", parameterList.getValueByName('p'));
	}
	
	@Test
	public void parseHandles2Parameters() throws Exception {
		givenParameters('p', 'q');
		givenArguments("-p", "pvalue", "-q", "qvalue");
		List<String> yetToParse = parameterList.parse(arguments);
		
		assertEquals(emptyList(), yetToParse);
		assertEquals("pvalue", parameterList.getValueByName('p'));
		assertEquals("qvalue", parameterList.getValueByName('q'));
	}
	
	@Test
	public void parseHandles2Parameters2() throws Exception {
		givenParameters('p', 'q');
		givenArguments("-p", "pvalue", "-q", "qvalue", "additional value");
		List<String> yetToParse = parameterList.parse(arguments);
		
		assertEquals(asList("additional value"), yetToParse);
		assertEquals("pvalue", parameterList.getValueByName('p'));
		assertEquals("qvalue", parameterList.getValueByName('q'));
	}
	
}
