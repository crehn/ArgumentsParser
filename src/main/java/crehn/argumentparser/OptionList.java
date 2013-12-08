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
		Option option = getOptionByArgument(yetToParse);
		if (option == null)
			return yetToParse;
		
		yetToParse = option.parse(yetToParse);
		return parse(yetToParse);
	}
	
	private Option getOptionByArgument(List<String> yetToParse)
	{
		if (yetToParse.size() == 0)
			return null;
		
		for (Option option : this)
		{
			String firstArgument = yetToParse.get(0);
			if (option.canHandle(firstArgument))
				return option;
		}
		return null;
	}
	
}
