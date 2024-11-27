package barista;

public abstract class CaffeineBeverageWithHook {

	// template method
	final void prepareRecipe() {
		boilWater();
		brew();
		pourInCup();
		if (customerWantsCondiments()) {
			addCondiments();			
		}
	}

	// hook
	protected boolean customerWantsCondiments() {
		return false;
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
