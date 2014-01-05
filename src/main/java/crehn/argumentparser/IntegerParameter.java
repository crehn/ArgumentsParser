package crehn.argumentparser;

public class IntegerParameter extends AbstractParameter<Integer>
{
	public IntegerParameter(char name)
	{
		super(name);
	}
	
	@Override
	protected Integer convertType(String string)
	{
		return Integer.valueOf(string);
	}
}
