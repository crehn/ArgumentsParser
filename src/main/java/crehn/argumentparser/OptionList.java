package crehn.argumentparser;

import java.util.ArrayList;
import java.util.List;

public class OptionList extends ArrayList<Option>
{
	private static final long serialVersionUID = 1L;
	
	public boolean isSpecifiedOption(char option)
	{
		return contains(new Option(option));
	}
	
	public List<String> parse(List<String> yetToParse)
	{
		Option option = getOptionByArguments(yetToParse);
		if (option == null)
			return yetToParse;
		
		yetToParse = option.parse(yetToParse);
		return parse(yetToParse);
	}
	
	private Option getOptionByArguments(List<String> yetToParse)
	{
		if (yetToParse.isEmpty())
			return null;
		
		for (Option option : this)
		{
			String firstArgument = yetToParse.get(0);
			if (option.canHandle(firstArgument))
				return option;
		}
		return null;
	}
	
	public Boolean isOptionSet(char optionName)
	{
		Option option = getOptionByName(optionName);
		if (option == null)
			throw new IllegalArgumentException("Unknown Option: " + optionName);
		
		return option.getIsSet();
	}
	
	private Option getOptionByName(char optionName)
	{
		for (Option option : this)
		{
			if (option.getName() == optionName)
				return option;
		}
		return null;
	}
	
}
