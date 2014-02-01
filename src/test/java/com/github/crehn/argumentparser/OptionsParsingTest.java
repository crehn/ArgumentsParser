package com.github.crehn.argumentparser;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class OptionsParsingTest {
	
	ArgumentParser parser;
	
	@Before
	public void setUp() {
		parser = new ArgumentParser();
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void specifyingOptionTwiceThrows() throws Exception {
		parser.specifyOption('o');
		parser.specifyOption('o');
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void conflictingOptionSpecificationThrows() throws Exception {
		parser.specifyStringParameter('o');
		parser.specifyOption('o');
	}
	
	@Test(expected = UnknownArgumentException.class)
	public void parseThrowsWhenUnspecifiedOption() throws Exception {
		parser.parse("-o");
	}
	
	@Test(expected = UnknownArgumentException.class)
	public void parseThrowsWhenAFurtherUnspecifiedOption() throws Exception {
		parser.specifyOption('o');
		
		parser.parse("-o", "-u");
	}
	
	@Test(expected = UnknownArgumentException.class)
	public void parseThrowsWhenAFurtherUnspecifiedConcatenatedOption() throws Exception {
		parser.specifyOption('o');
		
		parser.parse("-ou");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void isOptionSetThrowsWhenOptionNotSpecified() throws Exception {
		parser.parse();
		
		assertFalse(parser.isOptionSet('o'));
	}
	
	@Test
	public void isOptionSetRetrurnsFalseWhenParseNotCalled() throws Exception {
		parser.specifyOption('o');
		
		assertFalse(parser.isOptionSet('o'));
	}
	
	@Test
	public void isOptionSetReturnsFalseForEmptyArgs() throws Exception {
		parser.specifyOption('o');
		parser.parse();
		
		assertFalse(parser.isOptionSet('o'));
	}
	
	@Test
	public void isOptionSetReturnsTrueWhenOptionIsSet() throws Exception {
		parser.specifyOption('o');
		parser.parse("-o");
		
		assertTrue(parser.isOptionSet('o'));
	}
	
	@Test
	public void isOptionSetReturnsFalseWhenOptionIsNotSet() throws Exception {
		parser.specifyOption('o');
		parser.specifyOption('u');
		parser.parse("-o");
		
		assertFalse(parser.isOptionSet('u'));
	}
	
	@Test
	public void isOptionSetReturnsTrueWhenSecondOptionSet() throws Exception {
		parser.specifyOption('o');
		parser.specifyOption('u');
		parser.parse("-o", "-u");
		
		assertTrue(parser.isOptionSet('o'));
		assertTrue(parser.isOptionSet('u'));
	}
	
	@Test
	public void isOptionSetReturnsTrueWhenConcatenatedOptionSet() throws Exception {
		parser.specifyOption('o');
		parser.specifyOption('u');
		parser.parse("-ou");
		
		assertTrue(parser.isOptionSet('o'));
		assertTrue(parser.isOptionSet('u'));
	}
}
