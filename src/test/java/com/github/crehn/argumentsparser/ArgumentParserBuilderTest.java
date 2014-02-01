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
	
	@Before
	public void setup() {
		buildParser = new ArgumentParserBuilder();
	}
	
	@Test
	public void buildsEmptyParser() throws Exception {
		ArgumentParser parser = buildParser.build();
		
		assertNotNull(parser);
	}
	
	@Test
	public void buildsParserWithOptions() throws Exception {
		ArgumentParser parser = buildParser.withOption('o').andOption('p').build();
		
		assertTrue(parser.isSpecified('o'));
		assertTrue(parser.isSpecified('p'));
		assertEquals(Option.class, parser.getArgumentByName('o').getClass());
		assertEquals(Option.class, parser.getArgumentByName('p').getClass());
	}
	
	@Test
	public void buildsParserWithStringParams() throws Exception {
		ArgumentParser parser = buildParser.withStringParameter('p').andStringParameter('q').build();
		
		assertTrue(parser.isSpecified('p'));
		assertTrue(parser.isSpecified('q'));
		assertEquals(StringParameter.class, parser.getArgumentByName('p').getClass());
		assertEquals(StringParameter.class, parser.getArgumentByName('q').getClass());
	}
	
	@Test
	public void buildsParserWithIntegerParams() throws Exception {
		ArgumentParser parser = buildParser.withIntegerParameter('p').andIntegerParameter('q').build();
		
		assertTrue(parser.isSpecified('p'));
		assertTrue(parser.isSpecified('q'));
		assertEquals(IntegerParameter.class, parser.getArgumentByName('p').getClass());
		assertEquals(IntegerParameter.class, parser.getArgumentByName('q').getClass());
	}
	
	@Test
	public void buildsParserWithDoubleParams() throws Exception {
		ArgumentParser parser = buildParser.withDoubleParameter('p').andDoubleParameter('q').build();
		
		assertTrue(parser.isSpecified('p'));
		assertTrue(parser.isSpecified('q'));
		assertEquals(DoubleParameter.class, parser.getArgumentByName('p').getClass());
		assertEquals(DoubleParameter.class, parser.getArgumentByName('q').getClass());
	}
	
	@Test
	public void buildsParserWithStringListParams1() throws Exception {
		ArgumentParser parser = buildParser.withStringListParameter('p').build();
		
		assertTrue(parser.isSpecified('p'));
		assertEquals(StringListParameter.class, parser.getArgumentByName('p').getClass());
	}
	
	@Test
	public void buildsParserWithStringListParams2() throws Exception {
		ArgumentParser parser = buildParser.andStringListParameter('q').build();
		
		assertTrue(parser.isSpecified('q'));
		assertEquals(StringListParameter.class, parser.getArgumentByName('q').getClass());
	}
}
