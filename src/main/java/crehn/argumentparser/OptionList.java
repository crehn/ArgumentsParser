package crehn.argumentparser;

import java.util.ArrayList;

public class OptionList extends ArrayList<Character>
{
	private static final long serialVersionUID = 1L;
	
	public boolean isSpecifiedOption(char option)
	{
		return contains(option);
	}
	
}
