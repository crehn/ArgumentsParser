package crehn.argumentparser;

public class UnknownArgumentException extends ArgumentParsingException
{
	private static final long serialVersionUID = 1L;
	
	public UnknownArgumentException(String arg)
	{
		super("Unknown argument: " + arg);
	}
	
}
