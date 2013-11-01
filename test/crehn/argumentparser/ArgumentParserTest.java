package crehn.argumentparser;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

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
	
	@Test
	public void isOptionSetReturnsFalseForEmptyArgs() throws Exception
	{
		parser.parse(new String[] {});
		
		assertFalse(parser.isOptionSet('o'));
	}
	
	@Test(expected = ArgumentParsingException.class)
	public void isOptionSetReturnsFalseWhenNoOptionIsSpecified() throws Exception
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
	
	@Test(expected = ArgumentParsingException.class)
	public void parseThrowsWhenUnspecifiedOption() throws Exception
	{
		parser.parse(new String[] { "-o" });
	}
	
	@Test(expected = ArgumentParsingException.class)
	public void parseThrowsWhenAFurtherUnspecifiedOption() throws Exception
	{
		parser.specifyOption('o');
		
		parser.parse(new String[] { "-o", "-u" });
	}
	
	@Test(expected = ArgumentParsingException.class)
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
		
		assertTrue(parser.isOptionSet('u'));
	}
	
	@Test
	public void isOptionSetReturnsTrueWhenConcatenatedOptionSet() throws Exception
	{
		parser.specifyOption('o');
		parser.specifyOption('u');
		parser.parse(new String[] { "-ou" });
		
		assertTrue(parser.isOptionSet('u'));
	}
	
}
