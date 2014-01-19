package crehn.argumentparser;

class DoubleParameter extends AbstractParameter<Double> {
	public DoubleParameter(char name) {
		super(name);
	}
	
	@Override
	protected Double convertType(String string) {
		return Double.valueOf(string);
	}
}
