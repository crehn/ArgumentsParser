package crehn.argumentparser;

import java.util.List;

import lombok.Data;

@Data
public class Parameter
{
	private final char name;
	private String value;
	
	public List<String> parse(List<String> yetToParse) throws ParameterValueMissingException
	{
		if (!canHandle(yetToParse))
			return yetToParse;
		if (yetToParse.size() == 1)
			throw new ParameterValueMissingException(yetToParse.get(0));
		
		value = yetToParse.get(1);
		return yetToParse.subList(2, yetToParse.size());
	}
	
	public boolean canHandle(List<String> yetToParse)
	{
		if (yetToParse.isEmpty())
			return false;
		
		String firstArgument = yetToParse.get(0);
		return canHandle(firstArgument);
	}
	
	private boolean canHandle(String argument)
	{
		return argument.length() == 2 && argument.equals("-" + name);
	}
}
