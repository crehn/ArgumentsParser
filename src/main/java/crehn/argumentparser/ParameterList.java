package crehn.argumentparser;

import java.util.*;

public class ParameterList extends ArrayList<Parameter>
{
	private static final long serialVersionUID = 1L;
	
	public boolean isSpecifiedParameter(char paramName)
	{
		return contains(new Parameter(paramName));
	}
	
	public List<String> parse(List<String> yetToParse)
	{
		return Collections.EMPTY_LIST;
	}
	
}
