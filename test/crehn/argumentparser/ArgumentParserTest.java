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
	public void isOptionSetShouldReturnFalseForEmptyArgs() throws Exception
	{
		parser.parse(new String[] {});
		assertFalse(parser.isOptionSet("o"));
	}
	
	@Test
	public void isOptionSetShouldReturnFalseWhenNoOptionIsSpecified() throws Exception
	{
		parser.parse(new String[] { "dummy text" });
		assertFalse(parser.isOptionSet("o"));
	}
	
	@Test
	public void isOptionSetReturnsTrueWhenOptionIsSet() throws Exception
	{
		parser.specifyOption("o");
		parser.parse(new String[] { "-o" });
		assertTrue(parser.isOptionSet("o"));
	}
	
	@Test(expected = ArgumentParsingException.class)
	public void parseThrowsWhenUnspecifiedOption() throws Exception
	{
		parser.parse(new String[] { "-o" });
	}
	
}
