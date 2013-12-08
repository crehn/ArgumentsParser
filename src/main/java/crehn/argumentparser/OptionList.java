package crehn.argumentparser;

import java.util.*;

public class OptionList extends ArrayList<Option>
{
	private static final long serialVersionUID = 1L;
	
	public boolean isSpecifiedOption(char option)
	{
		return contains(new Option(option));
	}
	
	public List<String> parse(List<String> yetToParse)
	{
		if (yetToParse.size() == 0)
			return Collections.emptyList();
		
		Option option = null;
		while (yetToParse.size() > 0 && (option = getOptionByArgument(yetToParse.get(0))) != null)
		{
			option.parse(yetToParse.get(0));
			yetToParse = yetToParse.subList(1, yetToParse.size());
		}
		return yetToParse;
	}
	
	private Option getOptionByArgument(String argument)
	{
		for (Option option : this)
		{
			if (option.canHandle(argument))
				return option;
		}
		return null;
	}
	
}
