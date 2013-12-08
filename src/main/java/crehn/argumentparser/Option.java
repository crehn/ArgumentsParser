package crehn.argumentparser;

import lombok.Data;

@Data
public class Option
{
	private final char name;
	private Boolean isSet = null;
	
	public void parse(String argument)
	{
		isSet = optionString().equals(argument);
	}

	private String optionString()
	{
		return "-" + name;
	}
	
	public boolean canHandle(String argument)
	{
		return optionString().equals(argument);
	}
}
