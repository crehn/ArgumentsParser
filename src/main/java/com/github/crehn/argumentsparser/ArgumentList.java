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

class ArgumentList<T> extends ArrayList<Argument<T>> {
	private static final long serialVersionUID = 1L;
	
	public boolean isSpecified(Character shortArgumentName) {
		if (shortArgumentName == null)
			return false;
		
		return getArgumentByName(shortArgumentName) != null;
	}
	
	Argument<T> getArgumentByName(Character argumentName) {
		for (Argument<T> arg : this) {
			if (arg.getShortName() == argumentName)
				return arg;
		}
		return null;
	}
	
	public boolean isSpecified(String longArgumentName) {
		if (longArgumentName == null)
			return false;
		
		return getArgumentByName(longArgumentName) != null;
	}
	
	private Argument<T> getArgumentByName(String longArgumentName) {
		for (Argument<T> arg : this) {
			if (arg.getLongName() == longArgumentName)
				return arg;
		}
		return null;
	}
	
	public List<String> parse(List<String> yetToParse) throws ArgumentParsingException {
		Argument<T> argument = getArgumentByListToParse(yetToParse);
		if (argument == null)
			return yetToParse;
		
		yetToParse = argument.parse(yetToParse);
		return parse(yetToParse);
	}
	
	private Argument<T> getArgumentByListToParse(List<String> yetToParse) {
		if (yetToParse.isEmpty())
			return null;
		
		for (Argument<T> arg : this) {
			if (arg.canHandle(yetToParse))
				return arg;
		}
		return null;
	}
	
	public T getValueByName(Character shortArgumentName) {
		Argument<T> arg = getArgumentByName(shortArgumentName);
		if (arg == null)
			throw new IllegalArgumentException("Argument is not specified: " + shortArgumentName);
		
		return arg.getValue();
	}
	
	public T getValueByName(String longArgumentName) {
		Argument<T> arg = getArgumentByName(longArgumentName);
		if (arg == null)
			throw new IllegalArgumentException("Argument is not specified: " + longArgumentName);
		
		return arg.getValue();
	}
	
}