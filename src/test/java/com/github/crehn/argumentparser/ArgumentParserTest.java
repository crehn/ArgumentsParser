package com.github.crehn.argumentparser;

import org.junit.Before;
import org.junit.Test;

public class ArgumentParserTest {
	
	ArgumentParser parser;
	
	@Before
	public void setUp() {
		parser = new ArgumentParser();
	}
	
	@Test(expected = UnexpectedArgumentException.class)
	public void parseThrowsWhenOnlyEmptyString() throws Exception {
		parser.parse("");
	}
	
	@Test(expected = UnexpectedArgumentException.class)
	public void parseThrowsWhenOnlyDash() throws Exception {
		parser.specifyOption('o');
		
		parser.parse("-");
	}
}