package crehn.argumentparser;

import java.util.ArrayList;
import java.util.List;

public class ArgumentParser
{
	private String[] args;
	private final List<Character> specifiedOptions = new ArrayList<>();
	private final List<Character> specifiedParameters = new ArrayList<>();
	
	public void parse(String[] args) throws ArgumentParsingException
	{
		throwIfNotValid(args);
		
		this.args = args;
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
		return specifiedParameters.contains(arg.charAt(1));
	}
	
	private boolean isValue(String arg)
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
			if (!isSpecifiedOption(c))
				throw new UnknownArgumentException(c);
		}
	}
	
	private boolean isSpecifiedOption(char option)
	{
		return specifiedOptions.contains(option);
	}
	
	private boolean isSpecifiedParameter(char option)
	{
		return specifiedParameters.contains(option);
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
		if (isSpecifiedOption(option) || isSpecifiedParameter(option))
			throw new IllegalArgumentException("Argument already specified: " + option);
		
		specifiedOptions.add(option);
	}
	
	public void specifyParameter(char parameterName)
	{
		if (isSpecifiedParameter(parameterName) || isSpecifiedOption(parameterName))
			throw new IllegalArgumentException("Argument already specified: " + parameterName);
		
		specifiedParameters.add(parameterName);
	}
	
}
