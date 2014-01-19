package crehn.argumentparser;

public class ParameterValueMissingException extends ArgumentParsingException {
	private static final long serialVersionUID = 1L;
	
	public ParameterValueMissingException(String parameterName) {
		super("Value for parameter missing: " + parameterName);
	}
	
}
