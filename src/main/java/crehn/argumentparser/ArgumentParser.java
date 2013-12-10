package crehn.argumentparser;

import static java.util.Arrays.asList;

import java.util.List;

public class ArgumentParser
{
	private String[] args;
	private final OptionList specifiedOptions = new OptionList();
	private final ParameterList specifiedParameters = new ParameterList();
	
	public void parse(String[] args) throws ArgumentParsingException
	{
		List<String> yetToParse = asList(args);
		while (!yetToParse.isEmpty())
		{
			int sizeBefore = yetToParse.size();
			yetToParse = specifiedOptions.parse(yetToParse);
			yetToParse = specifiedParameters.parse(yetToParse);
			if (!sizeHasChanged(yetToParse, sizeBefore))
			{
				if (looksLikeOptions(yetToParse.get(0)))
					throw new UnknownArgumentException(yetToParse.get(0).charAt(1));
				else
					throw new UnexpectedArgumentException(yetToParse.get(0));
			}
		}
		
		this.args = args;
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
