package com.github.crehn.argumentparser;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
class Option implements Argument<Boolean> {
	private final char name;
	private Boolean isSet = false;
	
	@Override
	public List<String> parse(List<String> yetToParse) {
		String firstArgument = yetToParse.get(0);
		isSet = optionPartOf(firstArgument).contains("" + this.name);
		return removeOptionFrom(yetToParse);
	}
	
	private String optionPartOf(String argument) {
		return argument.length() == 0 ? "" : argument.substring(1);
	}
	
	private List<String> removeOptionFrom(List<String> yetToParse) {
		List<String> result = new ArrayList<>(yetToParse);
		String firstArgument = result.get(0);
		firstArgument = removeOptionFrom(firstArgument);
		result.set(0, firstArgument);
		if (firstArgument.isEmpty())
			return result.subList(1, yetToParse.size());
		else
			return result;
	}
	
	private String removeOptionFrom(String argument) {
		if (argument.length() == 2)
			return argument.charAt(1) == name ? "" : argument;
		
		return argument.replaceFirst("" + this.name, "");
	}
	
	@Override
	public boolean canHandle(List<String> yetToParse) {
		String firstArgument = yetToParse.get(0);
		if (!looksLikeOptions(firstArgument))
			return false;
		
		return optionPartOf(firstArgument).contains("" + this.name);
	}
	
	private boolean looksLikeOptions(String argument) {
		return argument.startsWith("-") && argument.length() > 1;
	}
	
	@Override
	public Boolean getValue() {
		return getIsSet();
	}
}
