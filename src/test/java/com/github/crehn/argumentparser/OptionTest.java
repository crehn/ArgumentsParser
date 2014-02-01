package com.github.crehn.argumentparser;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class OptionTest {
	
	Option option;
	
	@Before
	public void setup() {
		option = new Option('o');
	}
	
	@Test
	public void parseSetsFalseWhenEmptyString() throws Exception {
		option.parse(asList(""));
		
		assertFalse(option.getIsSet());
	}
	
	@Test
	public void parseSetsFalseWhenOtherOption() throws Exception {
		List<String> yetToParse = option.parse(asList("-u"));
		
		assertFalse(option.getIsSet());
		assertEquals(asList("-u"), yetToParse);
	}
	
	@Test
	public void parseSetsFalseWhenMissingDash() throws Exception {
		List<String> yetToParse = option.parse(asList("o"));
		
		assertFalse(option.getIsSet());
		assertEquals(emptyList(), yetToParse);
	}
}
