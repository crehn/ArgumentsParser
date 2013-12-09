package crehn.argumentparser;

import static org.junit.Assert.*;

import java.util.*;

import org.junit.Before;
import org.junit.Test;

public class ParameterTest
{
	Parameter parameter;
	private List<String> arguments;
	
	@Before
	public void setup()
	{
		parameter = new Parameter('p');
	}
	
	private void givenArguments(String... arguments)
	{
		this.arguments = Arrays.asList(arguments);
	}
	
	@Test
	public void initiallyValueIsUnknown() throws Exception
	{
		assertNull(parameter.getValue());
	}
	
	@Test
	public void canHandleReturnsFalseForEmptyList() throws Exception
	{
		assertFalse(parameter.canHandle(Collections.<String> emptyList()));
	}
	
	@Test
	public void canHandleReturnsTrueForSpecifiedParameter() throws Exception
	{
		assertTrue(parameter.canHandle(Arrays.asList("-p")));
	}
	
	@Test
	public void canHandleReturnsFalseWhenDashIsMissing() throws Exception
	{
		assertFalse(parameter.canHandle(Arrays.asList("p")));
	}
	
	@Test
	public void canHandleReturnsFalseForOtherParameter() throws Exception
	{
		assertFalse(parameter.canHandle(Arrays.asList("-q")));
	}
	
	@Test
	public void canHandleReturnsTrueForSpecifiedParameterDespiteFollowing() throws Exception
	{
		assertTrue(parameter.canHandle(Arrays.asList("-p", "-q")));
	}
	
	@Test
	public void canHandleReturnsFalseForOtherParameterDespiteFollowing() throws Exception
	{
		assertFalse(parameter.canHandle(Arrays.asList("-q", "-p")));
	}
	
	@Test
	public void parseHandlesEmptyList() throws Exception
	{
		givenArguments();
		
		List<String> yetToParse = parameter.parse(arguments);
		
		assertEquals(Collections.emptyList(), yetToParse);
		assertNull(parameter.getValue());
	}
	
	@Test(expected = ParameterValueMissingException.class)
	public void parseThrowsWhenValueMissing() throws Exception
	{
		givenArguments("-p");
		
		List<String> yetToParse = parameter.parse(arguments);
		
		assertEquals(Collections.emptyList(), yetToParse);
		assertEquals("value", parameter.getValue());
	}
	
	@Test
	public void parseHandlesValue() throws Exception
	{
		givenArguments("-p", "value");
		
		List<String> yetToParse = parameter.parse(arguments);
		
		assertEquals(Collections.emptyList(), yetToParse);
		assertEquals("value", parameter.getValue());
	}
	
	@Test
	public void parseIgnoresWhenDashMissing() throws Exception
	{
		givenArguments("p", "value");
		
		List<String> yetToParse = parameter.parse(arguments);
		
		assertEquals(arguments, yetToParse);
		assertNull(parameter.getValue());
	}
}
