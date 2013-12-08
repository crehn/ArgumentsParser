package crehn.argumentparser;

import static org.junit.Assert.*;

import java.util.*;

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
	public void canHandleReturnsTrueForConcatenatedOptions() throws Exception
	{
		assertTrue(option.canHandle("-ou"));
	}
	
	@Test
	public void parseSetsFalseWhenEmptyString() throws Exception
	{
		option.parse(Arrays.asList(""));
		
		assertFalse(option.getIsSet());
	}
	
	@Test
	public void parseSetsTrueWhenExpectedOption() throws Exception
	{
		List<String> yetToParse = option.parse(Arrays.asList("-o"));
		
		assertTrue(option.getIsSet());
		assertEquals(Collections.emptyList(), yetToParse);
	}
	
	@Test
	public void parseSetsFalseWhenOtherOption() throws Exception
	{
		List<String> yetToParse = option.parse(Arrays.asList("-u"));
		
		assertFalse(option.getIsSet());
		assertEquals(Arrays.asList("-u"), yetToParse);
	}
	
	@Test
	public void parseSetsFalseWhenMissingDash() throws Exception
	{
		List<String> yetToParse = option.parse(Arrays.asList("o"));
		
		assertFalse(option.getIsSet());
		assertEquals(Collections.emptyList(), yetToParse);
	}
	
	@Test
	public void parseCanHandleConcatenatedOptions() throws Exception
	{
		List<String> yetToParse = option.parse(Arrays.asList("-ou"));
		
		assertTrue(option.getIsSet());
		assertEquals(Arrays.asList("-u"), yetToParse);
	}
	
	@Test
	public void parseCanHandleConcatenatedOptions2() throws Exception
	{
		List<String> yetToParse = option.parse(Arrays.asList("-uo"));
		
		assertTrue(option.getIsSet());
		assertEquals(Arrays.asList("-u"), yetToParse);
	}
}
