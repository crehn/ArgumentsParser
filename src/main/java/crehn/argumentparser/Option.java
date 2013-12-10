package crehn.argumentparser;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class Option
{
	private final char name;
	private Boolean isSet = false;
	
	public List<String> parse(List<String> yetToParse)
	{
		String firstArgument = yetToParse.get(0);
		isSet = optionPartOf(firstArgument).contains("" + this.name);
		return removeOptionFrom(yetToParse);
	}
	
	private String optionPartOf(String argument)
	{
		return argument.length() == 0 ? "" : argument.substring(1);
	}
	
	private List<String> removeOptionFrom(List<String> yetToParse)
	{
		List<String> result = new ArrayList<>(yetToParse);
		String firstArgument = result.get(0);
		firstArgument = removeOptionFrom(firstArgument);
		result.set(0, firstArgument);
		if (firstArgument.isEmpty())
			return result.subList(1, yetToParse.size());
		else
			return result;
	}
	
	private String removeOptionFrom(String argument)
	{
		if (argument.length() == 2)
			return argument.charAt(1) == name ? "" : argument;
		
		return argument.replaceFirst("" + this.name, "");
	}
	
	public boolean canHandle(String argument)
	{
		if (!looksLikeOptions(argument))
			return false;
		
		return optionPartOf(argument).contains("" + this.name);
	}
	
	private boolean looksLikeOptions(String argument)
	{
		return argument.startsWith("-");
	}
}
