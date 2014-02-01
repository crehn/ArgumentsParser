package com.github.crehn.argumentsparser;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.Collections;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class StringListParameterTest {
	
	StringListParameter param;
	
	@Before
	public void setup() {
		param = new StringListParameter('p');
	}
	
	@Test
	public void canHandleReturnsFalseForEmptyList() throws Exception {
		assertFalse(param.canHandle(Collections.<String> emptyList()));
	}
	
	@Test
	public void parseReturnsIdentityWhenCannotHanlde() throws Exception {
		List<String> yetToParse = param.parse(asList("-q"));
		
		assertEquals(asList("-q"), yetToParse);
	}
}
