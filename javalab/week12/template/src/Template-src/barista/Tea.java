package barista;

public class Tea extends CaffeineBeverage {

	@Override
	protected void brew() {
		System.out.println("Stepping the Tea");
	}

	@Override
	protected void addCondiments() {
		System.out.println("Adding Lemon");
	}

}
