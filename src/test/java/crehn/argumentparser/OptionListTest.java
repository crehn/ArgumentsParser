package crehn.argumentparser;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class OptionListTest
{
	private OptionList optionList;
	private List<String> args;
	
	@Before
	public void setup()
	{
		optionList = new OptionList();
		args = emptyList();
	}
	
	private void givenSpecifiedOptions(char... options)
	{
		for (char o : options)
			optionList.add(new Option(o));
	}
	
	private void givenArguments(String... arguments)
	{
		args = asList(arguments);
	}
	
	@Test
	public void parseHandlsEmptyList() throws Exception
	{
		List<String> yetToParse = optionList.parse(args);
		
		assertEquals(emptyList(), yetToParse);
	}
	
	@Test(expected = NullPointerException.class)
	public void parseHandlsNull() throws Exception
	{
		optionList.parse(null);
	}
	
	@Test
	public void parseReturnsIdentityWhenNoOptionSpecified() throws Exception
	{
		givenArguments("-o");
		
		List<String> yetToParse = optionList.parse(args);
		
		assertEquals(args, yetToParse);
	}
	
	@Test
	public void parseReturnsIdentityWhenOtherOptionSpecified() throws Exception
	{
		givenSpecifiedOptions('o');
		givenArguments("-u");
		
		List<String> yetToParse = optionList.parse(args);
		
		assertEquals(args, yetToParse);
	}
	
	@Test
	public void parseHandlesSingleSpecifiedOption() throws Exception
	{
		givenSpecifiedOptions('o');
		givenArguments("-o");
		
		List<String> yetToParse = optionList.parse(args);
		
		assertEquals(emptyList(), yetToParse);
	}
	
	@Test
	public void parseHandles2Options() throws Exception
	{
		givenSpecifiedOptions('o', 'u');
		givenArguments("-o", "-u");
		
		List<String> yetToParse = optionList.parse(args);
		
		assertEquals(emptyList(), yetToParse);
	}
	
	@Test
	public void parseHandles2Options2() throws Exception
	{
		givenSpecifiedOptions('o', 'u');
		givenArguments("-o", "-u", "-v");
		
		List<String> yetToParse = optionList.parse(args);
		
		assertEquals(asList("-v"), yetToParse);
	}
	
	@Test
	public void parseHandlesConcatenatedOptions() throws Exception
	{
		givenSpecifiedOptions('o', 'u');
		givenArguments("-ou");
		
		List<String> yetToParse = optionList.parse(args);
		
		assertEquals(emptyList(), yetToParse);
	}
	
	@Test
	public void parseHandlesConcatenatedOptions2() throws Exception
	{
		givenSpecifiedOptions('o', 'u');
		givenArguments("-uo");
		
		List<String> yetToParse = optionList.parse(args);
		
		assertEquals(emptyList(), yetToParse);
	}
	
	@Test
	public void parseHandlesConcatenatedOptions3() throws Exception
	{
		givenSpecifiedOptions('o', 'u');
		givenArguments("-ouv");
		
		List<String> yetToParse = optionList.parse(args);
		
		assertEquals(asList("-v"), yetToParse);
	}
	
}
