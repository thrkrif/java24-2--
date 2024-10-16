package pizzaFactoryMethod;

public class ChicagoVeggiePizza extends Pizza {
	public ChicagoVeggiePizza() {
		name = "Chicago Style Veggie Pizza";
		dough = "Extra Thick Crust Dough";
		sauce = "Plum Tomato Sauce";
		toppings.add("Shredded Mozzarella Cheese");
		toppings.add("Black Olives");
		toppings.add("Spinach");
		toppings.add("Eggplant");
	}
	
	@Override
	public void cut() {
		System.out.println("Cutting the pizza into square slices.");
	}
}
