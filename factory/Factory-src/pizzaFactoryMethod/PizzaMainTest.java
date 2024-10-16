package pizzaFactoryMethod;

public class PizzaMainTest {

	public PizzaMainTest() {
		PizzaStore nyStore = new NYPizzaStore();
		PizzaStore chicagoStore = new ChicagoPizzaStore();

		String[] types = {"cheese", "clam", "pepperoni", "veggie"};
		Pizza pizza = null;
		for (String type : types) {
			pizza = nyStore.orderPizza(type);
			System.out.println("Ethan ordered a " + pizza.getName() + "\n\n");
			
			pizza = chicagoStore.orderPizza(type);
			System.out.println("Joel ordered a " + pizza.getName() + "\n\n");
		}
	}

}
