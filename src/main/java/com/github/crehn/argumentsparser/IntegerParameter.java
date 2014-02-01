package com.github.crehn.argumentsparser;

class IntegerParameter extends AbstractParameter<Integer> {
	public IntegerParameter(char name) {
		super(name);
	}
	
	@Override
	protected Integer convertType(String string) {
		return Integer.valueOf(string);
	}
}
