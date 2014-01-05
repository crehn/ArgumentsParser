package crehn.argumentparser;

import java.util.List;

import lombok.Data;

@Data
public abstract class AbstractParameter<T> implements Argument<T>
{
	private final char name;
	private T value;
	
	@Override
	public List<String> parse(List<String> yetToParse) throws ArgumentParsingException
	{
		if (!canHandle(yetToParse))
			return yetToParse;
		if (yetToParse.size() == 1)
			throw new ParameterValueMissingException(yetToParse.get(0));
		
		value = convertType(yetToParse.get(1));
		return yetToParse.subList(2, yetToParse.size());
	}
	
	protected abstract T convertType(String string);
	
	@Override
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