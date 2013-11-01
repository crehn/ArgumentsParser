package crehn.argumentparser;

import java.util.ArrayList;
import java.util.List;

public class ArgumentParser
{
	private String[] args;
	private final List<Character> specifiedOptions = new ArrayList<>();
	
	public void parse(String[] args) throws ArgumentParsingException
	{
		for (String arg : args)
		{
			if (looksLikeOptions(arg))
			{
				for (char c : arg.substring(1).toCharArray())
				{
					if (!isSpecifiedOption(c))
						throw new ArgumentParsingException("Unknown option " + c);
				}
			}
			else
			{
				throw new ArgumentParsingException("Unexpected argument: " + arg);
			}
		}
		
		this.args = args;
	}
	
	private boolean looksLikeOptions(String arg)
	{
		return arg.startsWith("-");
	}
	
	private boolean isSpecifiedOption(char option)
	{
		return specifiedOptions.contains(option);
	}
	
	public boolean isOptionSet(char option)
	{
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
	
	public void specifyOption(char option)
	{
		specifiedOptions.add(option);
	}
	
}
