package pizzaFactoryMethod;

public class ChicagoPizzaStore extends PizzaStore {

	@Override
	public Pizza createPizza(String type) {
		if (type.equals("cheese"))
			return new ChicagoCheesePizza();
		else if (type.equals("pepperoni"))
			return new ChicagoPepperoniPizza();
		else if (type.equals("clam"))
			return new ChicagoClamPizza();
		else if (type.equals("veggie"))
			return new ChicagoVeggiePizza();
		else
			return null;
	}

}
