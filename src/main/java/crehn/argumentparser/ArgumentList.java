package crehn.argumentparser;

import java.util.ArrayList;
import java.util.List;

class ArgumentList<T> extends ArrayList<Argument<T>> {
	private static final long serialVersionUID = 1L;
	
	public boolean isSpecified(char argumentName) {
		return getArgumentByName(argumentName) != null;
	}
	
	Argument<T> getArgumentByName(char argumentName) {
		for (Argument<T> arg : this) {
			if (arg.getName() == argumentName)
				return arg;
		}
		return null;
	}
	
	public List<String> parse(List<String> yetToParse) throws ArgumentParsingException {
		Argument<T> parameter = getArgumentByListToParse(yetToParse);
		if (parameter == null)
			return yetToParse;
		
		yetToParse = parameter.parse(yetToParse);
		return parse(yetToParse);
	}
	
	private Argument<T> getArgumentByListToParse(List<String> yetToParse) {
		if (yetToParse.isEmpty())
			return null;
		
		for (Argument<T> arg : this) {
			if (arg.canHandle(yetToParse))
				return arg;
		}
		return null;
	}
	
	public T getValueByName(char argumentName) {
		Argument<T> arg = getArgumentByName(argumentName);
		if (arg == null)
			throw new IllegalArgumentException("Argument is not specified: " + argumentName);
		
		return arg.getValue();
	}
	
}