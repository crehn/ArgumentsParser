package com.github.crehn.argumentsparser;

public class UnexpectedArgumentException extends ArgumentParsingException {
	private static final long serialVersionUID = 1L;
	
	public UnexpectedArgumentException(String arg) {
		super("Unexpected argument: " + arg);
	}
	
}
