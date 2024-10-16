package pizzaSimpleFactory;

public class PizzaStore {
	SimplePizzaFactory factory;

	public PizzaStore(SimplePizzaFactory factory) {
		this.factory = factory;
	}

	public Pizza orderPizza(String type) {
		Pizza pizza = factory.createPizza(type); // creates concrete Pizza object
		// factory 만든 덕에 if-else 안써도 됨.
		pizza.prepare();
		pizza.bake();
		pizza.cut();
		pizza.box();
		return pizza;
	}
}
