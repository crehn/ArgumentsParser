package crehn.argumentparser;

import java.util.ArrayList;
import java.util.List;

public class ParameterList extends ArrayList<Parameter>
{
	private static final long serialVersionUID = 1L;
	
	public boolean isSpecifiedParameter(char paramName)
	{
		return contains(new Parameter(paramName));
	}
	
	public List<String> parse(List<String> yetToParse) throws ArgumentParsingException
	{
		Parameter parameter = getParameterByArgument(yetToParse);
		if (parameter == null)
			return yetToParse;
		
		yetToParse = parameter.parse(yetToParse);
		return parse(yetToParse);
	}
	
	private Parameter getParameterByArgument(List<String> yetToParse)
	{
		if (yetToParse.isEmpty())
			return null;
		
		for (Parameter p : this)
		{
			if (p.canHandle(yetToParse))
				return p;
		}
		return null;
	}
	
	public String getByName(char paramName)
	{
		Parameter param = getParameterByName(paramName);
		if (param == null)
			throw new IllegalArgumentException("Parameter is not specified: " + paramName);
		
		return param.getValue();
	}
	
	private Parameter getParameterByName(char paramName)
	{
		for (Parameter param : this)
		{
			if (param.getName() == paramName)
				return param;
		}
		return null;
	}
	
}
