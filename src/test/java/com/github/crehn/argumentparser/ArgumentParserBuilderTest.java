package com.github.crehn.argumentparser;

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
