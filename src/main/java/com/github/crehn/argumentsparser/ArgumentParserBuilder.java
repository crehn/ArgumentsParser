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

public class ArgumentParserBuilder {
	ArgumentParser parser = new ArgumentParser();
	
	public ArgumentParser build() {
		return parser;
	}
	
	public ArgumentParserBuilder withOption(char optionName) {
		parser.specifyOption(optionName);
		return this;
	}
	
	public ArgumentParserBuilder andOption(char optionName) {
		return withOption(optionName);
	}
	
	public ArgumentParserBuilder withStringParameter(char parameterName) {
		parser.specifyStringParameter(parameterName);
		return this;
	}
	
	public ArgumentParserBuilder andStringParameter(char parameterName) {
		return withStringParameter(parameterName);
	}
	
	public ArgumentParserBuilder withIntegerParameter(char parameterName) {
		parser.specifyIntegerParameter(parameterName);
		return this;
	}
	
	public ArgumentParserBuilder andIntegerParameter(char parameterName) {
		return withIntegerParameter(parameterName);
	}
	
	public ArgumentParserBuilder withDoubleParameter(char parameterName) {
		parser.specifyDoubleParameter(parameterName);
		return this;
	}
	
	public ArgumentParserBuilder andDoubleParameter(char parameterName) {
		return withDoubleParameter(parameterName);
	}
	
	public ArgumentParserBuilder withStringListParameter(char parameterName) {
		parser.specifyStringListParameter(parameterName);
		return this;
	}
	
	public ArgumentParserBuilder andStringListParameter(char parameterName) {
		return withStringListParameter(parameterName);
	}
	
}
