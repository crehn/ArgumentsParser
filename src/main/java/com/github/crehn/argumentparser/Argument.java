package com.github.crehn.argumentparser;

import java.util.List;

interface Argument<T> {
	boolean canHandle(List<String> yetToParse);
	
	List<String> parse(List<String> yetToParse) throws ArgumentParsingException;
	
	char getName();
	
	T getValue();
}