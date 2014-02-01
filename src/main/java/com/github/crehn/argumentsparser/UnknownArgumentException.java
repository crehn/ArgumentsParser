package com.github.crehn.argumentsparser;

public class UnknownArgumentException extends ArgumentParsingException {
	private static final long serialVersionUID = 1L;
	
	public UnknownArgumentException(char c) {
		super("Unknown argument: " + c);
	}
	
}
