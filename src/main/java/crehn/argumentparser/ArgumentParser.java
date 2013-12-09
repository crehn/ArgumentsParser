package crehn.argumentparser;

import java.util.Arrays;
import java.util.List;

public class ArgumentParser
{
	private String[] args;
	private final OptionList specifiedOptions = new OptionList();
	private final ParameterList specifiedParameters = new ParameterList();
	
	public void parse(String[] args) throws ArgumentParsingException
	{
		throwIfNotValid(args);
		
		List<String> yetToParse = Arrays.asList(args);
		while (!yetToParse.isEmpty())
		{
			int sizeBefore = yetToParse.size();
			yetToParse = specifiedOptions.parse(yetToParse);
			yetToParse = specifiedParameters.parse(yetToParse);
			if (!sizeHasChanged(yetToParse, sizeBefore))
			{
				throw new UnknownArgumentException(yetToParse.get(0).charAt(1));
			}
		}
		
		this.args = args;
	}
	
	private boolean sizeHasChanged(List<String> yetToParse, int sizeBefore)
	{
		return yetToParse.size() != sizeBefore;
	}
	
	//
	//
	// validation
	
	private void throwIfNotValid(String[] args) throws ArgumentParsingException
	{
		boolean expectValueNext = false;
		for (String arg : args)
		{
			if (isSpecifiedParameter(arg))
			{
				expectValueNext = true;
			}
			else if (expectValueNext && isValue(arg))
			{
				expectValueNext = false;
			}
			else if (looksLikeOptions(arg))
			{
				throwIfContainsUnknownOptions(arg);
			}
			else
			{
				throw new UnexpectedArgumentException(arg);
			}
		}
		
		if (expectValueNext)
			throw new ParameterValueMissingException(args[args.length - 1]);
	}
	
	private boolean isSpecifiedParameter(String arg)
	{
		return specifiedParameters.contains(new Parameter(arg.charAt(1)));
	}
	
	private boolean isValue(@SuppressWarnings("unused") String arg)
	{
		return true;
	}
	
	private boolean looksLikeOptions(String arg)
	{
		return arg.startsWith("-");
	}
	
	private void throwIfContainsUnknownOptions(String arg) throws UnknownArgumentException
	{
		for (char c : arg.substring(1).toCharArray())
		{
			if (!specifiedOptions.isSpecifiedOption(c))
				throw new UnknownArgumentException(c);
		}
	}
	
	//
	//
	// queries
	
	public boolean isOptionSet(char option)
	{
		if (args == null)
			throw new IllegalStateException("You need to call parse() first.");
		
		for (String arg : args)
		{
			if (looksLikeOptions(arg))
			{
				for (char c : arg.toCharArray())
				{
					if (c == option)
						return true;
				}
			}
		}
		return false;
	}
	
	public String getParameter(char c)
	{
		if (args == null)
			throw new IllegalStateException("You need to call parse() first.");
		
		boolean valueExpectedNow = false;
		for (String arg : args)
		{
			if (valueExpectedNow)
				return arg;
			
			if (("-" + c).equals(arg))
				valueExpectedNow = true;
		}
		
		return null;
	}
	
	//
	//
	// defines
	
	public void specifyOption(char option)
	{
		if (specifiedOptions.isSpecifiedOption(option) || specifiedParameters.isSpecifiedParameter(option))
			throw new IllegalArgumentException("Argument already specified: " + option);
		
		specifiedOptions.add(new Option(option));
	}
	
	public void specifyParameter(char parameterName)
	{
		if (specifiedParameters.isSpecifiedParameter(parameterName)
				|| specifiedOptions.isSpecifiedOption(parameterName))
			throw new IllegalArgumentException("Argument already specified: " + parameterName);
		
		specifiedParameters.add(new Parameter(parameterName));
	}
	
}
