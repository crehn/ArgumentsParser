package crehn.argumentparser;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class OptionTest
{
	@Test
	public void canHandleReturnsFalseForEmptyString() throws Exception
	{
		Option option = new Option('o');
		
		assertFalse(option.canHandle(""));
	}
	
	@Test
	public void canHandleReturnsFalseWhenDashIsMissing() throws Exception
	{
		Option option = new Option('o');
		
		assertFalse(option.canHandle("o"));
	}
	
	@Test
	public void canHandleReturnsFalseWhenOtherChar() throws Exception
	{
		Option option = new Option('o');
		
		assertFalse(option.canHandle("-u"));
	}
	
	@Test
	public void canHandleReturnsTrue() throws Exception
	{
		Option option = new Option('o');
		
		assertTrue(option.canHandle("-o"));
	}
}
