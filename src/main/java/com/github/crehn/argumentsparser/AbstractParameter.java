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

import java.util.List;

import lombok.Data;

@Data
abstract class AbstractParameter<T> implements Argument<T> {
	private final String longName;
	private final Character shortName;
	private T value;
	
	@Override
	public List<String> parse(List<String> yetToParse) throws ArgumentParsingException {
		if (!canHandle(yetToParse))
			return yetToParse;
		if (yetToParse.size() == 1)
			throw new ParameterValueMissingException(yetToParse.get(0));
		if (value != null)
			throw new ParameterAlreadyOccuredException(longName);
		
		value = convertType(yetToParse.get(1));
		return yetToParse.subList(2, yetToParse.size());
	}
	
	protected abstract T convertType(String string);
	
	@Override
	public boolean canHandle(List<String> yetToParse) {
		if (yetToParse.isEmpty())
			return false;
		
		String firstArgument = yetToParse.get(0);
		return canHandle(firstArgument);
	}
	
	private boolean canHandle(String argument) {
		return ("-" + shortName).equals(argument) || ("--" + longName).equals(argument);
	}
}