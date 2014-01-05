package crehn.argumentparser;

import java.util.List;

public interface Argument
{
	
	boolean canHandle(List<String> yetToParse);
	
	List<String> parse(List<String> yetToParse) throws ArgumentParsingException;
	
}