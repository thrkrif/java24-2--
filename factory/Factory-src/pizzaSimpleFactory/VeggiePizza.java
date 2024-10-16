package pizzaSimpleFactory;

public class VeggiePizza extends Pizza {
	public VeggiePizza() {
		name = "Veggie Pizza";
		dough = "Crust";
		sauce = "Marinara Sauce";
		toppings.add("Shredded mozzarella");
		toppings.add("Grated parmesan cheese");
		toppings.add("Diced Onion");
		toppings.add("Sliced Mushrooms");
		toppings.add("Sliced Red Pepper");
		toppings.add("Sliced Black Olives");
	}
}
