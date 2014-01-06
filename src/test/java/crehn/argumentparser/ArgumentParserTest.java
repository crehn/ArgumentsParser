package crehn.argumentparser;

import static java.util.Arrays.asList;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ArgumentParserTest
{
	private ArgumentParser parser;
	
	@Before
	public void setUp()
	{
		parser = new ArgumentParser();
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void specifyingOptionTwiceThrows() throws Exception
	{
		parser.specifyOption('o');
		parser.specifyOption('o');
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void specifyingParameterTwiceThrows() throws Exception
	{
		parser.specifyStringParameter('o');
		parser.specifyStringParameter('o');
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void specifyingParameterTwiceThrows2() throws Exception
	{
		parser.specifyStringParameter('o');
		parser.specifyIntegerParameter('o');
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void specifyingParameterTwiceThrows3() throws Exception
	{
		parser.specifyStringParameter('o');
		parser.specifyDoubleParameter('o');
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void specifyingParameterTwiceThrows4() throws Exception
	{
		parser.specifyStringParameter('o');
		parser.specifyStringListParameter('o');
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void conflictingOptionSpecificationThrows() throws Exception
	{
		parser.specifyStringParameter('o');
		parser.specifyOption('o');
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void specifyingTwoListParametersThrows() throws Exception
	{
		parser.specifyStringListParameter('o');
		parser.specifyStringListParameter('p');
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void isOptionSetThrowsWhenOptionNotSpecified() throws Exception
	{
		parser.parse(new String[] {});
		
		assertFalse(parser.isOptionSet('o'));
	}
	
	@Test
	public void isOptionSetRetrurnsFalseWhenParseNotCalled() throws Exception
	{
		parser.specifyOption('o');
		
		assertFalse(parser.isOptionSet('o'));
	}
	
	@Test
	public void isOptionSetReturnsFalseForEmptyArgs() throws Exception
	{
		parser.specifyOption('o');
		parser.parse(new String[] {});
		
		assertFalse(parser.isOptionSet('o'));
	}
	
	@Test
	public void isOptionSetReturnsTrueWhenOptionIsSet() throws Exception
	{
		parser.specifyOption('o');
		parser.parse(new String[] { "-o" });
		
		assertTrue(parser.isOptionSet('o'));
	}
	
	@Test
	public void isOptionSetReturnsFalseWhenOptionIsNotSet() throws Exception
	{
		parser.specifyOption('o');
		parser.specifyOption('u');
		parser.parse(new String[] { "-o" });
		
		assertFalse(parser.isOptionSet('u'));
	}
	
	@Test
	public void isOptionSetReturnsTrueWhenSecondOptionSet() throws Exception
	{
		parser.specifyOption('o');
		parser.specifyOption('u');
		parser.parse(new String[] { "-o", "-u" });
		
		assertTrue(parser.isOptionSet('o'));
		assertTrue(parser.isOptionSet('u'));
	}
	
	@Test
	public void isOptionSetReturnsTrueWhenConcatenatedOptionSet() throws Exception
	{
		parser.specifyOption('o');
		parser.specifyOption('u');
		parser.parse(new String[] { "-ou" });
		
		assertTrue(parser.isOptionSet('o'));
		assertTrue(parser.isOptionSet('u'));
	}
	
	@Test(expected = UnknownArgumentException.class)
	public void parseThrowsWhenUnspecifiedOption() throws Exception
	{
		parser.parse(new String[] { "-o" });
	}
	
	@Test(expected = UnknownArgumentException.class)
	public void parseThrowsWhenAFurtherUnspecifiedOption() throws Exception
	{
		parser.specifyOption('o');
		
		parser.parse(new String[] { "-o", "-u" });
	}
	
	@Test(expected = UnknownArgumentException.class)
	public void parseThrowsWhenAFurtherUnspecifiedConcatenatedOption() throws Exception
	{
		parser.specifyOption('o');
		
		parser.parse(new String[] { "-ou" });
	}
	
	@Test(expected = UnexpectedArgumentException.class)
	public void parseThrowsWhenUnexpectedArguments() throws Exception
	{
		parser.parse(new String[] { "dummy text" });
	}
	
	@Test(expected = NumberFormatException.class)
	public void parseThrowsWhenWrongParameterTypeInteger() throws Exception
	{
		parser.specifyIntegerParameter('p');
		
		parser.parse(new String[] { "-p", "not an int" });
	}
	
	@Test(expected = NumberFormatException.class)
	public void parseThrowsWhenWrongParameterTypeDouble() throws Exception
	{
		parser.specifyDoubleParameter('p');
		
		parser.parse(new String[] { "-p", "not a double" });
	}
	
	@Test(expected = UnexpectedArgumentException.class)
	public void parseThrowsIfTwoParameterValues() throws Exception
	{
		parser.specifyStringParameter('p');
		parser.parse(new String[] { "-p", "one", "two" });
	}
	
	@Test(expected = ArgumentParsingException.class)
	public void getParameterThrowsWhenNotSpecified() throws Exception
	{
		parser.parse(new String[] { "-p", "value" });
	}
	
	@Test(expected = ParameterValueMissingException.class)
	public void getParameterThrowsWhenArgumentMissing() throws Exception
	{
		parser.specifyStringParameter('p');
		parser.parse(new String[] { "-p" });
	}
	
	@Test(expected = UnexpectedArgumentException.class)
	public void getParameterThrowsWhenArgumentMissing2() throws Exception
	{
		parser.specifyStringParameter('p');
		parser.specifyOption('o');
		parser.parse(new String[] { "-p", "-o", "value" });
	}
	
	@Test
	public void getParameterReturnsNullWhenParseNotCalled() throws Exception
	{
		parser.specifyStringParameter('p');
		
		assertNull(parser.getParameter('p'));
	}
	
	@Test
	public void getParameterReturnsNullWhenNotSet() throws Exception
	{
		parser.specifyStringParameter('p');
		parser.parse(new String[] {});
		
		assertNull(parser.getParameter('p'));
	}
	
	@Test
	public void getParameterReturnsStringValueWhenSet() throws Exception
	{
		parser.specifyStringParameter('p');
		parser.parse(new String[] { "-p", "value" });
		
		assertEquals("value", parser.getParameter('p'));
	}
	
	@Test
	public void getParameterReturnsIntegerValueWhenSet() throws Exception
	{
		parser.specifyIntegerParameter('p');
		parser.parse(new String[] { "-p", "42" });
		
		assertEquals(42, parser.getParameter('p'));
		assertEquals(Integer.valueOf(42), parser.<Integer> getParameter('p'));
	}
	
	@Test(expected = ClassCastException.class)
	public void getParameterThrowsWhenWrongTypeRequested() throws Exception
	{
		parser.specifyIntegerParameter('p');
		parser.parse(new String[] { "-p", "42" });
		
		@SuppressWarnings("unused")
		String d = parser.<String> getParameter('p');
	}
	
	@Test
	public void getParameterReturnsDoubleValueWhenSet() throws Exception
	{
		parser.specifyDoubleParameter('p');
		parser.specifyDoubleParameter('q');
		parser.parse(new String[] { "-p", "42", "-q", "12.34" });
		
		assertEquals(42.0, parser.getParameter('p'));
		assertEquals(Double.valueOf(42), parser.<Double> getParameter('p'));
		assertEquals(12.34, parser.getParameter('q'));
		assertEquals(Double.valueOf(12.34), parser.<Double> getParameter('q'));
	}
	
	@Test
	public void getParameterReturnsStringListValueWhenSet() throws Exception
	{
		parser.specifyStringListParameter('p');
		parser.parse(new String[] { "-p", "42", "-q", "12.34" });
		
		assertEquals(asList("42", "-q", "12.34"), parser.getParameter('p'));
	}
}