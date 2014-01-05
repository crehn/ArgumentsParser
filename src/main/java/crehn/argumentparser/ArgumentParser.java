package crehn.argumentparser;

import static java.util.Arrays.asList;

import java.util.List;

public class ArgumentParser
{
	private String[] args;
	private final ArgumentList<Boolean> options = new ArgumentList<>();
	private final ArgumentList<String> parameters = new ArgumentList<>();
	
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
		
		return options.getValueByName(option);
	}
	
	public String getParameter(char paramName)
	{
		if (args == null)
			throw new IllegalStateException("You need to call parse() first.");
		
		return parameters.getValueByName(paramName);
	}
	
	public void specifyOption(char option)
	{
		if (options.isSpecified(option) || parameters.isSpecified(option))
			throw new IllegalArgumentException("Argument already specified: " + option);
		
		options.add(new Option(option));
	}
	
	public void specifyParameter(char parameterName)
	{
		if (parameters.isSpecified(parameterName) || options.isSpecified(parameterName))
			throw new IllegalArgumentException("Argument already specified: " + parameterName);
		
		parameters.add(new Parameter(parameterName));
	}
	
}
