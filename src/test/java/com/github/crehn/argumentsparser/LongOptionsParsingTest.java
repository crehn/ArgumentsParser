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
	
	@Test
	public void shouldReturnFalseForEmptyArgument() throws Exception {
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
}
