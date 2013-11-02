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
			else if (areSpecifiedOptions(arg))
			{
				// ok
			}
			else
			{
				throw new UnknownArgumentException(arg);
			}
		}
		
		this.args = args;
	}
	
	private boolean isSpecifiedParameter(String arg)
	{
		return specifiedParameters.contains(arg.charAt(1));
	}
	
	private boolean isValue(String arg)
	{
		return true;
	}
	
	private boolean areSpecifiedOptions(String arg)
	{
		for (char c : arg.substring(1).toCharArray())
		{
			if (!isSpecifiedOption(c))
				return false;
		}
		
		return true;
	}
	
	private boolean isSpecifiedOption(char option)
	{
		return specifiedOptions.contains(option);
	}
	
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
					{
						return true;
					}
				}
			}
		}
		return false;
	}
	
	private boolean looksLikeOptions(String arg)
	{
		return arg.startsWith("-");
	}
	
	public void specifyOption(char option)
	{
		if (isSpecifiedOption(option) || isSpecifiedParameter(option))
			throw new IllegalArgumentException("Option already specified: " + option);
		
		specifiedOptions.add(option);
	}
	
	private boolean isSpecifiedParameter(char option)
	{
		return specifiedParameters.contains(option);
	}
	
	public String getParameter(char c)
	{
		if (args == null)
			throw new IllegalStateException("You need to call parse() first.");
		
		boolean valueExpectedNow = false;
		for (String arg : args)
		{
			if (valueExpectedNow)
			{
				return arg;
			}
			
			if (("-" + c).equals(arg))
			{
				valueExpectedNow = true;
			}
		}
		
		return null;
	}
	
	public void specifyParameter(char parameterName)
	{
		if (isSpecifiedParameter(parameterName) || isSpecifiedOption(parameterName))
			throw new IllegalArgumentException("Argument already specified: " + parameterName);
		
		specifiedParameters.add(parameterName);
	}
	
}
