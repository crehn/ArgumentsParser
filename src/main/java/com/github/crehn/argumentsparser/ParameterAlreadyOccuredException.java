package com.github.crehn.argumentsparser;

public class ParameterAlreadyOccuredException extends ArgumentParsingException {
	private static final long serialVersionUID = 1L;
	
	public ParameterAlreadyOccuredException(char paramName) {
		super("Parameter already specified: " + paramName);
	}
}
