package crehn.argumentparser;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class IntegerParameterTest
{
	IntegerParameter parameter;
	private List<String> arguments;
	
	@Before
	public void setup()
	{
		parameter = new IntegerParameter('p');
	}
	
	private void givenArguments(String... arguments)
	{
		this.arguments = asList(arguments);
	}
	
	@Test
	public void parseIntegerValue() throws Exception
	{
		givenArguments("-p", "42");
		
		List<String> yetToParse = parameter.parse(arguments);
		
		assertEquals(emptyList(), yetToParse);
		assertEquals(Integer.valueOf(42), parameter.getValue());
	}
	
	@Test(expected = NumberFormatException.class)
	public void throwWhenStringValue() throws Exception
	{
		givenArguments("-p", "not an int");
		
		parameter.parse(arguments);
	}
	
}
