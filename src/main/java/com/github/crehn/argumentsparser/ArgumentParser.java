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

import static java.util.Arrays.asList;

import java.util.List;

// due to type erasure it's impossible to use type arguments for the
// arguments list, so raw types are used and the warnings are ignored
@SuppressWarnings({ "unchecked" })
public class ArgumentParser {
	
	@SuppressWarnings({ "rawtypes" })
	private final ArgumentList arguments = new ArgumentList();
	
	private boolean catchAllArgAlreadySpecified = false;
	
	public void parse(String... args) throws ArgumentParsingException {
		List<String> yetToParse = asList(args);
		while (!yetToParse.isEmpty()) {
			int sizeBefore = yetToParse.size();
			yetToParse = arguments.parse(yetToParse);
			if (!sizeHasChanged(yetToParse, sizeBefore)) {
				if (looksLikeOptions(yetToParse.get(0)))
					throw new UnknownArgumentException(yetToParse.get(0).charAt(1));
				else
					throw new UnexpectedArgumentException(yetToParse.get(0));
			}
		}
	}
	
	private boolean sizeHasChanged(List<String> yetToParse, int sizeBefore) {
		return yetToParse.size() != sizeBefore;
	}
	
	private boolean looksLikeOptions(String arg) {
		return arg.startsWith("-") && arg.length() > 1;
	}
	
	public void specifyOption(char shortOptionName) {
		specifyOption(null, shortOptionName);
	}
	
	public void specifyOption(String longOptionName) {
		specifyOption(longOptionName, null);
	}
	
	public void specifyOption(String longOptionName, Character shortOptionName) {
		if (arguments.isSpecified(shortOptionName))
			throw new IllegalArgumentException("Argument already specified: " + shortOptionName);
		if (arguments.isSpecified(longOptionName))
			throw new IllegalArgumentException("Argument already specified: " + longOptionName);
		
		arguments.add(new Option(longOptionName, shortOptionName));
	}
	
	boolean isSpecified(char shortArgumentName) {
		return arguments.isSpecified(shortArgumentName);
	}
	
	boolean isSpecified(String longArgumentName) {
		return arguments.isSpecified(longArgumentName);
	}
	
	public void specifyStringParameter(char parameterName) {
		if (arguments.isSpecified(parameterName))
			throw new IllegalArgumentException("Argument already specified: " + parameterName);
		
		arguments.add(new StringParameter(parameterName));
	}
	
	public void specifyIntegerParameter(char parameterName) {
		if (arguments.isSpecified(parameterName))
			throw new IllegalArgumentException("Argument already specified: " + parameterName);
		
		arguments.add(new IntegerParameter(parameterName));
	}
	
	public void specifyDoubleParameter(char parameterName) {
		if (arguments.isSpecified(parameterName))
			throw new IllegalArgumentException("Argument already specified: " + parameterName);
		
		arguments.add(new DoubleParameter(parameterName));
	}
	
	public void specifyStringListParameter(char parameterName) {
		if (arguments.isSpecified(parameterName) || catchAllArgAlreadySpecified)
			throw new IllegalArgumentException("Argument already specified: " + parameterName);
		
		arguments.add(new StringListParameter(null, parameterName));
		catchAllArgAlreadySpecified = true;
	}
	
	public boolean isOptionSet(char shortOptionName) {
		return (Boolean) arguments.getValueByName(shortOptionName);
	}
	
	public boolean isOptionSet(String longOptionName) {
		return (Boolean) arguments.getValueByName(longOptionName);
	}
	
	public <T> T getParameter(char paramName) {
		return (T) arguments.getValueByName(paramName);
	}
	
	<T> Argument<T> getArgumentByName(char argumentName) {
		return arguments.getArgumentByName(argumentName);
	}
}