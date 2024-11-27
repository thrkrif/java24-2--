package barista;

public abstract class CaffeineBeverage {

	// template method
	final void prepareRecipe() {
		boilWater();
		brew();
		pourInCup();
		addCondiments();
	}

	// primitiveOperations
	protected abstract void brew();
	protected abstract void addCondiments();

	// concreteOperations
	private void boilWater() {
		System.out.println("Boiling Water");
	}

	private void pourInCup() {
		System.out.println("Pouring into Cup");
	}

}
