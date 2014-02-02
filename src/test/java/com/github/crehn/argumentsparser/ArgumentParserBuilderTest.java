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

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ArgumentParserBuilderTest {
	
	ArgumentParserBuilder buildParser;
	private ArgumentParser parser;
	
	@Before
	public void setup() {
		buildParser = new ArgumentParserBuilder();
	}
	
	private <T> void assertSpecified(Class<? extends Argument<T>> argumentType, String... names) {
		for (String name : names) {
			assertTrue(parser.isSpecified(name));
			assertEquals(argumentType, parser.getArgumentByName(name).getClass());
		}
	}
	
	private <T> void assertSpecified(Class<? extends Argument<T>> argumentType, Character... names) {
		for (Character name : names) {
			assertTrue(parser.isSpecified(name));
			assertEquals(argumentType, parser.getArgumentByName(name).getClass());
		}
	}
	
	@Test
	public void shouldBuildEmptyParser() throws Exception {
		parser = buildParser.build();
		
		assertNotNull(parser);
	}
	
	@Test
	public void shouldBuildParserWithOptions() throws Exception {
		parser = buildParser.withOption("long-option", 'l').andOption("other-option", 'o').build();
		
		assertSpecified(Option.class, "long-option", "other-option");
		assertSpecified(Option.class, 'l', 'o');
	}
	
	@Test
	public void shouldBuildParserWithOptionsWithoutShortForm() throws Exception {
		parser = buildParser.withOption("long-option").andOption("other-option").build();
		
		assertSpecified(Option.class, "long-option", "other-option");
	}
	
	@Test
	public void shouldBuildParserWithStringParams() throws Exception {
		parser = buildParser.withStringParameter("long-parameter", 'l').andStringParameter("other-parameter", 'o')
				.build();
		
		assertSpecified(StringParameter.class, "long-parameter", "other-parameter");
		assertSpecified(StringParameter.class, 'l', 'o');
	}
	
	@Test
	public void shouldBuildParserWithStringParamsWithoutShortForm() throws Exception {
		parser = buildParser.withStringParameter("long-parameter").andStringParameter("other-parameter").build();
		
		assertSpecified(StringParameter.class, "long-parameter", "other-parameter");
	}
	
	@Test
	public void shouldBuildParserWithIntegerParams() throws Exception {
		parser = buildParser.withIntegerParameter("long-parameter", 'l').andIntegerParameter("other-parameter", 'o')
				.build();
		
		assertSpecified(IntegerParameter.class, "long-parameter", "other-parameter");
		assertSpecified(IntegerParameter.class, 'l', 'o');
	}
	
	@Test
	public void shouldBuildParserWithIntegerParamsWithoutShortForm() throws Exception {
		parser = buildParser.withIntegerParameter("long-parameter").andIntegerParameter("other-parameter").build();
		
		assertSpecified(IntegerParameter.class, "long-parameter", "other-parameter");
	}
	
	@Test
	public void shouldBuildParserWithDoubleParams() throws Exception {
		parser = buildParser.withDoubleParameter("long-parameter", 'l').andDoubleParameter("other-parameter", 'o')
				.build();
		
		assertSpecified(DoubleParameter.class, "long-parameter", "other-parameter");
		assertSpecified(DoubleParameter.class, 'l', 'o');
	}
	
	@Test
	public void shouldBuildParserWithDoubleParamsWithoutShortForm() throws Exception {
		parser = buildParser.withDoubleParameter("long-parameter").andDoubleParameter("other-parameter").build();
		
		assertSpecified(DoubleParameter.class, "long-parameter", "other-parameter");
	}
	
	@Test
	public void shouldBuildParserWithStringListParams() throws Exception {
		parser = buildParser.withStringListParameter("long-parameter", 'p').build();
		
		assertSpecified(StringListParameter.class, "long-parameter");
		assertSpecified(StringListParameter.class, 'p');
	}
	
	@Test
	public void shouldBuildParserWithStringListParamsWithoutShortForm() throws Exception {
		parser = buildParser.withStringListParameter("long-parameter").build();
		
		assertSpecified(StringListParameter.class, "long-parameter");
	}
	
	@Test
	public void shouldBuildParserWithStringListParams2() throws Exception {
		parser = buildParser.andStringListParameter("long-parameter", 'p').build();
		
		assertSpecified(StringListParameter.class, "long-parameter");
		assertSpecified(StringListParameter.class, 'p');
	}
	
	@Test
	public void shouldBuildParserWithStringListParams2WithoutShortForm() throws Exception {
		parser = buildParser.andStringListParameter("long-parameter").build();
		
		assertSpecified(StringListParameter.class, "long-parameter");
	}
}
