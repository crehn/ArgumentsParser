package crehn.argumentparser;

import static java.util.Arrays.asList;

import java.util.List;

// due to type erasure it's impossible to use type arguments for the arguments
// list, so raw types are used and the warnings are ignored
@SuppressWarnings({ "rawtypes", "unchecked" })
public class ArgumentParser
{
	private final ArgumentList arguments = new ArgumentList();
	private boolean catchAllArgAlreadySpecified = false;
	
	public void parse(String[] args) throws ArgumentParsingException
	{
		List<String> yetToParse = asList(args);
		while (!yetToParse.isEmpty())
		{
			int sizeBefore = yetToParse.size();
			yetToParse = arguments.parse(yetToParse);
			if (!sizeHasChanged(yetToParse, sizeBefore))
			{
				if (looksLikeOptions(yetToParse.get(0)))
					throw new UnknownArgumentException(yetToParse.get(0).charAt(1));
				else
					throw new UnexpectedArgumentException(yetToParse.get(0));
			}
		}
	}
	
	private boolean sizeHasChanged(List<String> yetToParse, int sizeBefore)
	{
		return yetToParse.size() != sizeBefore;
	}
	
	private boolean looksLikeOptions(String arg)
	{
		return arg.startsWith("-");
	}
	
	public boolean isOptionSet(char option)
	{
		Object value = arguments.getValueByName(option);
		return value instanceof Boolean && (Boolean) value;
	}
	
	public <T> T getParameter(char paramName)
	{
		return (T) arguments.getValueByName(paramName);
	}
	
	public void specifyOption(char optionName)
	{
		if (arguments.isSpecified(optionName))
			throw new IllegalArgumentException("Argument already specified: " + optionName);
		
		arguments.add(new Option(optionName));
	}
	
	public void specifyStringParameter(char parameterName)
	{
		if (arguments.isSpecified(parameterName))
			throw new IllegalArgumentException("Argument already specified: " + parameterName);
		
		arguments.add(new StringParameter(parameterName));
	}
	
	public void specifyIntegerParameter(char parameterName)
	{
		if (arguments.isSpecified(parameterName))
			throw new IllegalArgumentException("Argument already specified: " + parameterName);
		
		arguments.add(new IntegerParameter(parameterName));
	}
	
	public void specifyDoubleParameter(char parameterName)
	{
		if (arguments.isSpecified(parameterName))
			throw new IllegalArgumentException("Argument already specified: " + parameterName);
		
		arguments.add(new DoubleParameter(parameterName));
	}
	
	public void specifyStringListParameter(char parameterName)
	{
		if (arguments.isSpecified(parameterName) || catchAllArgAlreadySpecified)
			throw new IllegalArgumentException("Argument already specified: " + parameterName);
		
		arguments.add(new StringListParameter(parameterName));
		catchAllArgAlreadySpecified = true;
	}
	
	boolean isSpecified(char argumentName)
	{
		return arguments.isSpecified(argumentName);
	}
	
	<T> Argument<T> getArgumentByName(char argumentName)
	{
		return arguments.getArgumentByName(argumentName);
	}
}
