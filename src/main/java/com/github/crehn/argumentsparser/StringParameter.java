package com.github.crehn.argumentsparser;

class StringParameter extends AbstractParameter<String> {
	public StringParameter(char name) {
		super(name);
	}
	
	@Override
	protected String convertType(String string) {
		return string;
	}
}
