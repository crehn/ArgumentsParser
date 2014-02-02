package com.github.crehn.argumentsparser;

import org.junit.Before;
import org.junit.Test;

public class ArgumentListTest {
	
	ArgumentList<Option> argumentList;
	
	@Before
	public void setup() {
		argumentList = new ArgumentList<>();
	}
	
	@Test
	public void isSpecifiedReturnsFalseWhenNull() throws Exception {
		argumentList.isSpecified((String) null);
	}
}
