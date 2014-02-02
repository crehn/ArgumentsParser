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
	
	public ArgumentParserBuilder withOption(String longOptionName) {
		return withOption(longOptionName, null);
	}
	
	public ArgumentParserBuilder withOption(String longOptionName, Character shortOptionName) {
		parser.specifyOption(longOptionName, shortOptionName);
		return this;
	}
	
	public ArgumentParserBuilder andOption(String longOptionName) {
		return withOption(longOptionName);
	}
	
	public ArgumentParserBuilder andOption(String longOptionName, Character shortOptionName) {
		return withOption(longOptionName, shortOptionName);
	}
	
	public ArgumentParserBuilder withStringParameter(String longParamName) {
		return withStringParameter(longParamName, null);
	}
	
	public ArgumentParserBuilder withStringParameter(String longParamName, Character shortParamName) {
		parser.specifyStringParameter(longParamName, shortParamName);
		return this;
	}
	
	public ArgumentParserBuilder andStringParameter(String longParamName) {
		return withStringParameter(longParamName);
	}
	
	public ArgumentParserBuilder andStringParameter(String longParamName, Character shortParamName) {
		return withStringParameter(longParamName, shortParamName);
	}
	
	public ArgumentParserBuilder withIntegerParameter(String longParamName) {
		return withIntegerParameter(longParamName, null);
	}
	
	public ArgumentParserBuilder withIntegerParameter(String longParamName, Character shortParamName) {
		parser.specifyIntegerParameter(longParamName, shortParamName);
		return this;
	}
	
	public ArgumentParserBuilder andIntegerParameter(String longParamName) {
		return withIntegerParameter(longParamName);
	}
	
	public ArgumentParserBuilder andIntegerParameter(String longParamName, Character shortParamName) {
		return withIntegerParameter(longParamName, shortParamName);
	}
	
	public ArgumentParserBuilder withDoubleParameter(String longParamName) {
		return withDoubleParameter(longParamName, null);
	}
	
	public ArgumentParserBuilder withDoubleParameter(String longParamName, Character shortParamName) {
		parser.specifyDoubleParameter(longParamName, shortParamName);
		return this;
	}
	
	public ArgumentParserBuilder andDoubleParameter(String longParamName) {
		return withDoubleParameter(longParamName);
	}
	
	public ArgumentParserBuilder andDoubleParameter(String longParamName, Character shortParamName) {
		return withDoubleParameter(longParamName, shortParamName);
	}
	
	public ArgumentParserBuilder withStringListParameter(String longParamName) {
		return withStringListParameter(longParamName, null);
	}
	
	public ArgumentParserBuilder withStringListParameter(String longParamName, Character shortParamName) {
		parser.specifyStringListParameter(longParamName, shortParamName);
		return this;
	}
	
	public ArgumentParserBuilder andStringListParameter(String longParamName) {
		return withStringListParameter(longParamName);
	}
	
	public ArgumentParserBuilder andStringListParameter(String longParamName, Character shortParamName) {
		return withStringListParameter(longParamName, shortParamName);
	}
}
