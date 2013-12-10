package crehn.argumentparser;

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
	
	@Test(expected = IllegalStateException.class)
	public void isOptionSetThrowsWhenParseNotCalled() throws Exception
	{
		parser.isOptionSet('c');
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void isOptionSetThrowsWhenOptionNotSpecified() throws Exception
	{
		parser.parse(new String[] {});
		
		assertFalse(parser.isOptionSet('o'));
	}
	
	@Test
	public void isOptionSetReturnsFalseForEmptyArgs() throws Exception
	{
		parser.specifyOption('o');
		parser.parse(new String[] {});
		
		assertFalse(parser.isOptionSet('o'));
	}
	
	@Test(expected = UnexpectedArgumentException.class)
	public void parseThrowsWhenUnexpectedArguments() throws Exception
	{
		parser.parse(new String[] { "dummy text" });
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
	
	@Test(expected = IllegalStateException.class)
	public void getParameterThrowsWhenParseNotCalled() throws Exception
	{
		parser.getParameter('c');
	}
	
	@Test
	public void getParameterReturnsNullWhenNotSet() throws Exception
	{
		parser.parse(new String[] {});
		
		assertNull(parser.getParameter('p'));
	}
	
	@Test(expected = ArgumentParsingException.class)
	public void getParameterThrowsWhenNotSpecified() throws Exception
	{
		parser.parse(new String[] { "-p", "value" });
	}
	
	@Test
	public void getParameterReturnsValueWhenSet() throws Exception
	{
		parser.specifyParameter('p');
		parser.parse(new String[] { "-p", "value" });
		
		assertEquals("value", parser.getParameter('p'));
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
		parser.specifyParameter('o');
		parser.specifyParameter('o');
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void conflictingSpecificationThrows2() throws Exception
	{
		parser.specifyParameter('o');
		parser.specifyOption('o');
	}
	
	@Test(expected = UnexpectedArgumentException.class)
	public void parseThrowsIfTwoParameterValues() throws Exception
	{
		parser.specifyParameter('p');
		parser.parse(new String[] { "-p", "one", "two" });
	}
	
	@Test(expected = ParameterValueMissingException.class)
	public void getParameterThrowsWhenArgumentMissing() throws Exception
	{
		parser.specifyParameter('p');
		parser.parse(new String[] { "-p" });
	}
	
	@Test(expected = UnexpectedArgumentException.class)
	public void getParameterThrowsWhenArgumentMissing2() throws Exception
	{
		parser.specifyParameter('p');
		parser.specifyOption('o');
		parser.parse(new String[] { "-p", "-o", "value" });
	}
}