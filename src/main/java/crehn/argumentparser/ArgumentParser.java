package crehn.argumentparser;

import static java.util.Arrays.asList;

import java.util.List;

public class ArgumentParser
{
	private String[] args;
	private final OptionList options = new OptionList();
	private final ParameterList parameters = new ParameterList();
	
	public void parse(String[] args) throws ArgumentParsingException
	{
		List<String> yetToParse = asList(args);
		while (!yetToParse.isEmpty())
		{
			int sizeBefore = yetToParse.size();
			yetToParse = options.parse(yetToParse);
			yetToParse = parameters.parse(yetToParse);
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
		
		return options.isOptionSet(option);
	}
	
	public String getParameter(char paramName)
	{
		if (args == null)
			throw new IllegalStateException("You need to call parse() first.");
		
		return parameters.getByName(paramName);
	}
	
	public void specifyOption(char option)
	{
		if (options.isSpecifiedOption(option) || parameters.isSpecifiedParameter(option))
			throw new IllegalArgumentException("Argument already specified: " + option);
		
		options.add(new Option(option));
	}
	
	public void specifyParameter(char parameterName)
	{
		if (parameters.isSpecifiedParameter(parameterName) || options.isSpecifiedOption(parameterName))
			throw new IllegalArgumentException("Argument already specified: " + parameterName);
		
		parameters.add(new Parameter(parameterName));
	}
	
}
