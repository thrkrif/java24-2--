package pizzaFactoryMethod;

public class ChicagoClamPizza extends Pizza {
	public ChicagoClamPizza() {
		name = "Chicago Style Clam Pizza";
		dough = "Extra Thick Crust Dough";
		sauce = "Plum Tomato Sauce";
		toppings.add("Shredded Mozzarella Cheese");
		toppings.add("Frozen Clams from Chesapeake Bay");
	}
	
	@Override
	public void cut() {
		System.out.println("Cutting the pizza into square slices.");
	}

}
