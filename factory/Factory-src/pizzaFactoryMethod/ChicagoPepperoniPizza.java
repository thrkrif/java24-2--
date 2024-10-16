package pizzaFactoryMethod;

public class ChicagoPepperoniPizza extends Pizza {
	public ChicagoPepperoniPizza() {
		name = "Chicago Style Pepperoni Pizza";
		dough = "Extra Thick Crust Dough";
		sauce = "Plum Tomato Sauce";
		toppings.add("Shredded Mozzarella Cheese");
		toppings.add("Slices Pepperoni");
		toppings.add("Garlic");
		toppings.add("Onion");
	}
	
	@Override
	public void cut() {
		System.out.println("Cutting the pizza into square slices.");
	}
}
