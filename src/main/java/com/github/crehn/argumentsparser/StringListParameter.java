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

import static java.util.Collections.emptyList;

import java.util.List;

import lombok.Data;

@Data
class StringListParameter implements Argument<List<String>> {
	private final String longName;
	private final Character shortName;
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
		return ("-" + shortName).equals(argument);
	}
}
