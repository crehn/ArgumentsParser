package com.github.crehn.argumentparser;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;

public class ParametersParsingTest {
	
	ArgumentParser parser;
	
	@Before
	public void setUp() {
		parser = new ArgumentParser();
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void specifyingParameterTwiceThrows() throws Exception {
		parser.specifyStringParameter('p');
		parser.specifyStringParameter('p');
	}
	
	@Test(expected = UnexpectedArgumentException.class)
	public void parseThrowsWhenUnexpectedArguments() throws Exception {
		parser.parse("dummy text");
	}
	
	@Test(expected = UnexpectedArgumentException.class)
	public void parseThrowsIfTwoParameterValues() throws Exception {
		parser.specifyStringParameter('p');
		
		parser.parse("-p", "one", "two");
	}
	
	@Test(expected = UnknownArgumentException.class)
	public void parseThrowsWhenNotSpecified() throws Exception {
		parser.parse("-p", "value");
	}
	
	@Test(expected = ParameterValueMissingException.class)
	public void parseThrowsWhenArgumentMissing() throws Exception {
		parser.specifyStringParameter('p');
		parser.parse("-p");
	}
	
	@Test(expected = UnexpectedArgumentException.class)
	public void parseThrowsWhenArgumentMissing2() throws Exception {
		parser.specifyStringParameter('p');
		parser.specifyOption('o');
		parser.parse("-p", "-o", "value");
	}
	
	@Test
	public void getParameterReturnsNullWhenParseNotCalled() throws Exception {
		parser.specifyStringParameter('p');
		
		assertNull(parser.getParameter('p'));
	}
	
	@Test
	public void getParameterReturnsNullWhenNotSet() throws Exception {
		parser.specifyStringParameter('p');
		parser.parse();
		
		assertNull(parser.getParameter('p'));
	}
	
	@Test
	public void getParameterReturnsStringValueWhenSet() throws Exception {
		parser.specifyStringParameter('p');
		parser.parse("-p", "value");
		
		assertEquals("value", parser.getParameter('p'));
	}
	
	@Test
	public void getParameterReturnsEmptyStringValueWhenSet() throws Exception {
		parser.specifyStringParameter('p');
		parser.parse("-p", "");
		
		assertEquals("", parser.getParameter('p'));
	}
	
	@Test(expected = ClassCastException.class)
	public void isOptionSetThrowsWhenItsActuallyAParameter() throws Exception {
		parser.specifyStringParameter('p');
		parser.parse("-p", "value");
		
		parser.isOptionSet('p');
	}
	
}
