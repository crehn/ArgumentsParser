package com.github.crehn.argumentsparser;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;

public class ParametersParsingTest {
	
	ArgumentParser parser;
	
	@Before
	public void setup() {
		parser = new ArgumentParser();
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void shouldThrowWhenSpecifyingParameterTwice() throws Exception {
		parser.specifyStringParameter('p');
		parser.specifyStringParameter('p');
	}
	
	@Test(expected = UnexpectedArgumentException.class)
	public void shouldThrowWhenUnexpectedArguments() throws Exception {
		parser.parse("dummy text");
	}
	
	@Test(expected = UnexpectedArgumentException.class)
	public void shouldThrowWhenTwoParameterValues() throws Exception {
		parser.specifyStringParameter('p');
		
		parser.parse("-p", "one", "two");
	}
	
	@Test(expected = UnknownArgumentException.class)
	public void whouldThrowWhenNotSpecified() throws Exception {
		parser.parse("-p", "value");
	}
	
	@Test(expected = ParameterValueMissingException.class)
	public void shouldThrowWhenArgumentMissing() throws Exception {
		parser.specifyStringParameter('p');
		parser.parse("-p");
	}
	
	@Test(expected = UnexpectedArgumentException.class)
	public void shouldThrowWhenArgumentMissing2() throws Exception {
		parser.specifyStringParameter('p');
		parser.specifyOption('o');
		parser.parse("-p", "-o", "value");
	}
	
	@Test(expected = ClassCastException.class)
	public void shouldThrowWhenCallingIsOptionSetOnAParameter() throws Exception {
		parser.specifyStringParameter('p');
		parser.parse("-p", "value");
		
		parser.isOptionSet('p');
	}
	
	@Test(expected = ParameterAlreadyOccuredException.class)
	public void shouldThrowWhenParameterGivenTwice() throws Exception {
		parser.specifyStringParameter('p');
		parser.parse("-p", "value", "-p", "value2");
	}
	
	@Test
	public void shouldReturnNullWhenParseNotCalled() throws Exception {
		parser.specifyStringParameter('p');
		
		assertNull(parser.getParameter('p'));
	}
	
	@Test
	public void shouldReturnNullWhenNotSet() throws Exception {
		parser.specifyStringParameter('p');
		parser.parse();
		
		assertNull(parser.getParameter('p'));
	}
	
	@Test
	public void shouldReturnStringValueWhenSet() throws Exception {
		parser.specifyStringParameter('p');
		parser.parse("-p", "value");
		
		assertEquals("value", parser.getParameter('p'));
	}
	
	@Test
	public void shouldReturnEmptyStringValueWhenSet() throws Exception {
		parser.specifyStringParameter('p');
		parser.parse("-p", "");
		
		assertEquals("", parser.getParameter('p'));
	}
	
}
