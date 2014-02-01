package com.github.crehn.argumentsparser;

import static java.util.Collections.emptyList;

import java.util.List;

import lombok.Data;

@Data
class StringListParameter implements Argument<List<String>> {
	private final char name;
	private List<String> value;
	
	@Override
	public List<String> parse(List<String> yetToParse) {
		if (!canHandle(yetToParse))
			return yetToParse;
		
		value = yetToParse.subList(1, yetToParse.size());
		return emptyList();
	}
	
	@Override
	public boolean canHandle(List<String> yetToParse) {
		if (yetToParse.isEmpty())
			return false;
		
		String firstArgument = yetToParse.get(0);
		return canHandle(firstArgument);
	}
	
	private boolean canHandle(String argument) {
		return ("-" + name).equals(argument);
	}
}
