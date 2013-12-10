package crehn.argumentparser;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static org.junit.Assert.assertEquals;

import java.util.Collections;
import java.util.List;

import org.junit.*;

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
	public void parseHandlsEmptyList() throws Exception
	{
		List<String> yetToParse = parameterList.parse(Collections.<String> emptyList());
		
		assertEquals(emptyList(), yetToParse);
	}
	
	@Ignore
	@Test(expected = NullPointerException.class)
	public void parseHandlsNull() throws Exception
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
	
	@Test
	public void parseReturnsIdentityWhenOtherParameterSpecified() throws Exception
	{
		givenParameters('p');
		givenArguments("-q");
		List<String> yetToParse = parameterList.parse(arguments);
		
		assertEquals(arguments, yetToParse);
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
	}
	
	@Test
	public void parseHandles2Parameters() throws Exception
	{
		givenParameters('p', 'q');
		givenArguments("-p", "pvalue", "-q", "qvalue");
		List<String> yetToParse = parameterList.parse(arguments);
		
		assertEquals(emptyList(), yetToParse);
	}
	
	@Test
	public void parseHandles2Parameters2() throws Exception
	{
		givenParameters('p', 'q');
		givenArguments("-p", "pvalue", "-q", "qvalue", "additional value");
		List<String> yetToParse = parameterList.parse(arguments);
		
		assertEquals(asList("additional value"), yetToParse);
	}
	
}
