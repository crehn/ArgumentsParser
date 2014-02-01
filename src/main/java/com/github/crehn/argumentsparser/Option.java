/*
 *  Copyright 2013, 2014 Christian Rehn
 *
 *  This file is part of ArgumentsParser.
 *
 *  ArgumentsParser is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU Lesser General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  ArgumentsParser is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU Lesser General Public License for more details.
 *
 *  You should have received a copy of the GNU Lesser General Public License
 *  along with ArgumentsParser.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.github.crehn.argumentsparser;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
class Option implements Argument<Boolean> {
	private final String longName;
	private final Character shortName;
	private Boolean isSet = false;
	
	@Override
	public List<String> parse(List<String> yetToParse) {
		String firstArgument = yetToParse.get(0);
		isSet = parseArgument(firstArgument);
		return removeOptionFrom(yetToParse);
	}
	
	private boolean parseArgument(String argument) {
		return argument != null && (isShortForm(argument) || isLongForm(argument));
	}
	
	private boolean isShortForm(String argument) {
		return argument.startsWith("-") && argument.contains("" + shortName);
	}
	
	private boolean isLongForm(String argument) {
		return ("--" + longName).equals(argument);
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
		if (isLongForm(argument))
			return "";
		else
			return removeShortFormOfOption(argument);
	}
	
	private String removeShortFormOfOption(String argument) {
		if (argument.length() == 2)
			return argument.charAt(1) == shortName ? "" : argument;
		
		return argument.replaceFirst("" + this.shortName, "");
	}
	
	@Override
	public boolean canHandle(List<String> yetToParse) {
		String firstArgument = yetToParse.get(0);
		if (looksLikeShortOptions(firstArgument))
			return firstArgument.contains("" + shortName);
		else if (looksLikeLongOption(firstArgument))
			return firstArgument.equals("--" + longName);
		else
			return false;
	}
	
	private boolean looksLikeLongOption(String argument) {
		return argument.startsWith("--") && argument.length() > 2;
	}
	
	private boolean looksLikeShortOptions(String argument) {
		return argument.startsWith("-") && argument.length() > 1 && !argument.substring(1).contains("-");
	}
	
	@Override
	public Boolean getValue() {
		return getIsSet();
	}
}
