package crehn.argumentparser;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.Collections;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class ParameterListTest
{
	ParameterList parameterList;
	private List<String> arguments;
	
	@Before
	public void setup()
	{
		parameterList = new ParameterList();
	}
	
	private void givenArguments(String... arguments)
	{
		this.arguments = asList(arguments);
	}
	
	private void givenParameters(char... parameters)
	{
		for (char p : parameters)
			parameterList.add(new Parameter(p));
	}
	
	@Test
	public void parseHandlesEmptyList() throws Exception
	{
		List<String> yetToParse = parameterList.parse(Collections.<String> emptyList());
		
		assertEquals(emptyList(), yetToParse);
	}
	
	@Test(expected = NullPointerException.class)
	public void parseHandlesNull() throws Exception
	{
		parameterList.parse(null);
	}
	
	@Test
	public void parseReturnsIdentityWhenNoParameterSpecified() throws Exception
	{
		givenArguments("-p");
		List<String> yetToParse = parameterList.parse(arguments);
		
		assertEquals(arguments, yetToParse);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void getByNameThrowsWhenParameterNotSpecified() throws Exception
	{
		givenArguments("-p");
		parameterList.parse(arguments);
		
		parameterList.getByName('p');
	}
	
	@Test
	public void parseReturnsIdentityWhenOtherParameterSpecified() throws Exception
	{
		givenParameters('p');
		givenArguments("-q");
		List<String> yetToParse = parameterList.parse(arguments);
		
		assertEquals(arguments, yetToParse);
		assertNull(parameterList.getByName('p'));
	}
	
	@Test(expected = ParameterValueMissingException.class)
	public void parseThrowsWhenParameterValueMissing() throws Exception
	{
		givenParameters('p');
		givenArguments("-p");
		
		parameterList.parse(arguments);
	}
	
	@Test
	public void parseHandlesSingleSpecifiedParameter() throws Exception
	{
		givenParameters('p');
		givenArguments("-p", "value");
		List<String> yetToParse = parameterList.parse(arguments);
		
		assertEquals(emptyList(), yetToParse);
		assertEquals("value", parameterList.getByName('p'));
	}
	
	@Test
	public void parseHandles2Parameters() throws Exception
	{
		givenParameters('p', 'q');
		givenArguments("-p", "pvalue", "-q", "qvalue");
		List<String> yetToParse = parameterList.parse(arguments);
		
		assertEquals(emptyList(), yetToParse);
		assertEquals("pvalue", parameterList.getByName('p'));
		assertEquals("qvalue", parameterList.getByName('q'));
	}
	
	@Test
	public void parseHandles2Parameters2() throws Exception
	{
		givenParameters('p', 'q');
		givenArguments("-p", "pvalue", "-q", "qvalue", "additional value");
		List<String> yetToParse = parameterList.parse(arguments);
		
		assertEquals(asList("additional value"), yetToParse);
		assertEquals("pvalue", parameterList.getByName('p'));
		assertEquals("qvalue", parameterList.getByName('q'));
	}
	
}
