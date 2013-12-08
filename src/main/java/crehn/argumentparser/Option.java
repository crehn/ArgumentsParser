package crehn.argumentparser;

import lombok.Data;

@Data
public class Option
{
	private final char name;
	private boolean isSet = false;
	
	public void parse(String string)
	{
		
	}
	
	public boolean canHandle(String argument)
	{
		return ("-" + name).equals(argument);
	}
}
