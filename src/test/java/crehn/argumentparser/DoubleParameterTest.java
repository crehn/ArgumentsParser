package crehn.argumentparser;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class DoubleParameterTest {
	DoubleParameter parameter;
	private List<String> arguments;
	
	@Before
	public void setup() {
		parameter = new DoubleParameter('p');
	}
	
	private void givenArguments(String... arguments) {
		this.arguments = asList(arguments);
	}
	
	@Test
	public void parseIntegerValue() throws Exception {
		givenArguments("-p", "42");
		
		List<String> yetToParse = parameter.parse(arguments);
		
		assertEquals(emptyList(), yetToParse);
		assertEquals(Double.valueOf(42), parameter.getValue());
	}
	
	@Test
	public void parseDoubleValue() throws Exception {
		givenArguments("-p", "12.34");
		
		List<String> yetToParse = parameter.parse(arguments);
		
		assertEquals(emptyList(), yetToParse);
		assertEquals(Double.valueOf(12.34), parameter.getValue());
	}
	
	@Test(expected = NumberFormatException.class)
	public void throwWhenStringValue() throws Exception {
		givenArguments("-p", "not a double");
		
		parameter.parse(arguments);
	}
}
