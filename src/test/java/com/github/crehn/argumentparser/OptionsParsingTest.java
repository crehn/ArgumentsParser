package com.github.crehn.argumentparser;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class OptionsParsingTest {
	
	ArgumentParser parser;
	
	@Before
	public void setup() {
		parser = new ArgumentParser();
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void shouldThrowWhenSpecifyingOptionTwice() throws Exception {
		parser.specifyOption('o');
		parser.specifyOption('o');
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void shouldThrowWhenAlreadyAParameter() throws Exception {
		parser.specifyStringParameter('o');
		parser.specifyOption('o');
	}
	
	@Test(expected = UnknownArgumentException.class)
	public void shouldThrowWhenUnspecifiedOption() throws Exception {
		parser.parse("-o");
	}
	
	@Test(expected = UnknownArgumentException.class)
	public void shouldThrowWhenAFurtherUnspecifiedOption() throws Exception {
		parser.specifyOption('o');
		
		parser.parse("-o", "-u");
	}
	
	@Test(expected = UnknownArgumentException.class)
	public void shouldThrowWhenAFurtherUnspecifiedConcatenatedOption() throws Exception {
		parser.specifyOption('o');
		
		parser.parse("-ou");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void shouldThrowWhenOptionNotSpecified() throws Exception {
		parser.parse();
		
		assertFalse(parser.isOptionSet('o'));
	}
	
	@Test
	public void shouldReturnFalseWhenParseNotCalled() throws Exception {
		parser.specifyOption('o');
		
		assertFalse(parser.isOptionSet('o'));
	}
	
	@Test
	public void shouldReturnFalseForEmptyArgs() throws Exception {
		parser.specifyOption('o');
		parser.parse();
		
		assertFalse(parser.isOptionSet('o'));
	}
	
	@Test
	public void shouldReturnTrueWhenOptionIsSet() throws Exception {
		parser.specifyOption('o');
		parser.parse("-o");
		
		assertTrue(parser.isOptionSet('o'));
	}
	
	@Test
	public void shouldReturnFalseWhenOptionIsNotSet() throws Exception {
		parser.specifyOption('o');
		parser.specifyOption('u');
		parser.parse("-o");
		
		assertFalse(parser.isOptionSet('u'));
	}
	
	@Test
	public void shouldSetReturnTrueWhenSecondOptionSet() throws Exception {
		parser.specifyOption('o');
		parser.specifyOption('u');
		parser.parse("-o", "-u");
		
		assertTrue(parser.isOptionSet('o'));
		assertTrue(parser.isOptionSet('u'));
	}
	
	@Test
	public void shouldReturnTrueWhenConcatenatedOptionSet() throws Exception {
		parser.specifyOption('o');
		parser.specifyOption('u');
		parser.parse("-ou");
		
		assertTrue(parser.isOptionSet('o'));
		assertTrue(parser.isOptionSet('u'));
	}
}
