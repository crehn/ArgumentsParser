package crehn.argumentparser;

public class ArgumentParserBuilder {
	ArgumentParser parser = new ArgumentParser();
	
	public ArgumentParser build() {
		return parser;
	}
	
	public ArgumentParserBuilder withOption(char optionName) {
		parser.specifyOption(optionName);
		return this;
	}
	
	public ArgumentParserBuilder andOption(char optionName) {
		return withOption(optionName);
	}
	
	public ArgumentParserBuilder withStringParameter(char parameterName) {
		parser.specifyStringParameter(parameterName);
		return this;
	}
	
	public ArgumentParserBuilder andStringParameter(char parameterName) {
		return withStringParameter(parameterName);
	}
	
	public ArgumentParserBuilder withIntegerParameter(char parameterName) {
		parser.specifyIntegerParameter(parameterName);
		return this;
	}
	
	public ArgumentParserBuilder andIntegerParameter(char parameterName) {
		return withIntegerParameter(parameterName);
	}
	
	public ArgumentParserBuilder withDoubleParameter(char parameterName) {
		parser.specifyDoubleParameter(parameterName);
		return this;
	}
	
	public ArgumentParserBuilder andDoubleParameter(char parameterName) {
		return withDoubleParameter(parameterName);
	}
	
	public ArgumentParserBuilder withStringListParameter(char parameterName) {
		parser.specifyStringListParameter(parameterName);
		return this;
	}
	
	public ArgumentParserBuilder andStringListParameter(char parameterName) {
		return withStringListParameter(parameterName);
	}
	
}
