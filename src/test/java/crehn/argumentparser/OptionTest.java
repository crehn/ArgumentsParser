package crehn.argumentparser;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class OptionTest
{
	private Option option;
	
	@Before
	public void setup()
	{
		option = new Option('o');
	}
	
	@Test
	public void initiallyValueIsUnknown() throws Exception
	{
		assertNull(option.getIsSet());
	}
	
	@Test
	public void canHandleReturnsFalseForEmptyString() throws Exception
	{
		assertFalse(option.canHandle(""));
	}
	
	@Test
	public void canHandleReturnsFalseWhenDashIsMissing() throws Exception
	{
		assertFalse(option.canHandle("o"));
	}
	
	@Test
	public void canHandleReturnsFalseWhenOtherChar() throws Exception
	{
		assertFalse(option.canHandle("-u"));
	}
	
	@Test
	public void canHandleReturnsTrue() throws Exception
	{
		assertTrue(option.canHandle("-o"));
	}
	
	@Test
	public void parseParsesEmptyString() throws Exception
	{
		option.parse("");
		
		assertFalse(option.getIsSet());
	}
	
	@Test
	public void parseParsesOption() throws Exception
	{
		option.parse("-o");
		
		assertTrue(option.getIsSet());
	}
	
	@Test
	public void parseParsesOtherOption() throws Exception
	{
		option.parse("-u");
		
		assertFalse(option.getIsSet());
	}
	
	@Test
	public void parseParsesMissingDash() throws Exception
	{
		option.parse("o");
		
		assertFalse(option.getIsSet());
	}
}
