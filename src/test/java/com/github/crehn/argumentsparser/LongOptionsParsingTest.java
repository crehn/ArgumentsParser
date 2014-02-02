package com.github.crehn.argumentsparser;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class LongOptionsParsingTest {
	
	ArgumentParser parser;
	
	@Before
	public void setup() {
		parser = new ArgumentParser();
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void shouldThrowWhenSpecifyingOptionTwice() throws Exception {
		parser.specifyOption("long-option");
		parser.specifyOption("long-option");
	}
	
	@Test(expected = UnknownArgumentException.class)
	public void shouldThrowWhenUnspecifiedOption() throws Exception {
		parser.parse("--long-option");
	}
	
	@Test(expected = UnknownArgumentException.class)
	public void shouldThrowWhenAFurtherUnspecifiedOption() throws Exception {
		parser.specifyOption("long-option");
		
		parser.parse("--long-option", "--other-option");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void shouldThrowWhenOptionNotSpecified() throws Exception {
		parser.parse();
		
		assertFalse(parser.isOptionSet("long-option"));
	}
	
	@Test(expected = UnexpectedArgumentException.class)
	public void shouldThrowWhenOnlyTwoDashes() throws Exception {
		parser.specifyOption("long-option");
		
		parser.parse("--");
	}
	
	@Test
	public void shouldReturnFalseForEmptyArgument() throws Exception {
		parser.specifyOption("long-option");
		
		parser.parse();
		
		assertFalse(parser.isOptionSet("long-option"));
	}
	
	@Test
	public void shouldReturnFalseWhenParseNotCalled() throws Exception {
		parser.specifyOption("long-option");
		
		assertFalse(parser.isOptionSet("long-option"));
	}
	
	@Test
	public void shouldReturnFalseForEmptyArgs() throws Exception {
		parser.specifyOption("long-option");
		parser.parse();
		
		assertFalse(parser.isOptionSet("long-option"));
	}
	
	@Test
	public void shouldReturnTrueWhenOptionSet() throws Exception {
		parser.specifyOption("long-option");
		
		parser.parse("--long-option");
		
		assertTrue(parser.isOptionSet("long-option"));
	}
	
	@Test
	public void shouldReturnFalseWhenOptionNotSet() throws Exception {
		parser.specifyOption("long-option");
		parser.specifyOption("other-option");
		parser.parse("--other-option");
		
		assertFalse(parser.isOptionSet("long-option"));
	}
	
	@Test
	public void shouldSetReturnTrueWhenSecondOptionSet() throws Exception {
		parser.specifyOption("long-option");
		parser.specifyOption("other-option");
		parser.parse("--long-option", "--other-option");
		
		assertTrue(parser.isOptionSet("long-option"));
		assertTrue(parser.isOptionSet("other-option"));
	}
	
	@Test
	public void shouldReturnTrueWhenShortFormIsSet() throws Exception {
		parser.specifyOption("long-option", 'o');
		parser.parse("-o");
		
		assertTrue(parser.isOptionSet("long-option"));
	}
}
