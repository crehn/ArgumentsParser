package crehn.argumentparser;

import java.util.Arrays;

public class ArgumentParser
{
	private String[] args;
	private String option;
	
	public void parse(String[] args) throws ArgumentParsingException
	{
		if (args.length > 0 && args[0].startsWith("-") && !("-" + option).equals(args[0]))
			throw new ArgumentParsingException("Unknown option " + "-" + option);
		
		this.args = args;
	}
	
	public boolean isOptionSet(String option)
	{
		return Arrays.asList(args).contains("-" + option);
	}
	
	public void specifyOption(String option)
	{
		this.option = option;
	}
	
}
