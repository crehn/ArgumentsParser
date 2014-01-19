package crehn.argumentparser;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class OptionTest {
	private Option option;
	
	@Before
	public void setup() {
		option = new Option('o');
	}
	
	@Test
	public void initiallyValueIsUnknown() throws Exception {
		assertFalse(option.getIsSet());
	}
	
	@Test
	public void canHandleReturnsFalseForEmptyString() throws Exception {
		assertFalse(option.canHandle(asList("")));
	}
	
	@Test
	public void canHandleReturnsFalseWhenDashIsMissing() throws Exception {
		assertFalse(option.canHandle(asList("o")));
	}
	
	@Test
	public void canHandleReturnsFalseWhenOtherChar() throws Exception {
		assertFalse(option.canHandle(asList("-u")));
	}
	
	@Test
	public void canHandleReturnsTrue() throws Exception {
		assertTrue(option.canHandle(asList("-o")));
	}
	
	@Test
	public void canHandleReturnsTrueForConcatenatedOptions() throws Exception {
		assertTrue(option.canHandle(asList("-ou")));
	}
	
	@Test
	public void parseSetsFalseWhenEmptyString() throws Exception {
		option.parse(asList(""));
		
		assertFalse(option.getIsSet());
	}
	
	@Test
	public void parseSetsTrueWhenExpectedOption() throws Exception {
		List<String> yetToParse = option.parse(asList("-o"));
		
		assertTrue(option.getIsSet());
		assertEquals(emptyList(), yetToParse);
	}
	
	@Test
	public void parseSetsFalseWhenOtherOption() throws Exception {
		List<String> yetToParse = option.parse(asList("-u"));
		
		assertFalse(option.getIsSet());
		assertEquals(asList("-u"), yetToParse);
	}
	
	@Test
	public void parseSetsFalseWhenMissingDash() throws Exception {
		List<String> yetToParse = option.parse(asList("o"));
		
		assertFalse(option.getIsSet());
		assertEquals(emptyList(), yetToParse);
	}
	
	@Test
	public void parseCanHandleConcatenatedOptions() throws Exception {
		List<String> yetToParse = option.parse(asList("-ou"));
		
		assertTrue(option.getIsSet());
		assertEquals(asList("-u"), yetToParse);
	}
	
	@Test
	public void parseCanHandleConcatenatedOptions2() throws Exception {
		List<String> yetToParse = option.parse(asList("-uo"));
		
		assertTrue(option.getIsSet());
		assertEquals(asList("-u"), yetToParse);
	}
}
