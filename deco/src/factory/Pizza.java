package factory;

import java.util.ArrayList;

public abstract class Pizza {
	String name;
	String dough;
	String sauce;
	ArrayList<String> toppings = new ArrayList<>();
	
	public String getName() {
		return name;
	}
	
	public void prepare() {
		System.out.println("Preparing " + name);
		System.out.println("Tossing dough.." + dough);
		System.out.println("Adding sauce.." + sauce);
		System.out.println("Adding toppings: ");
		for (String topping : toppings) {
			System.out.println("     " + topping);
		}
	}
	
	public void bake() {
		System.out.println("Bake for 25 minutes at 350");		
	}
	
	public void cut() {
		System.out.println("Cut the pizza into diagonal slices");		
	}
	
	public void box() {
		System.out.println("Place pizza in official PizzaStore box");		
	}

	@Override
	public String toString() {
		return "Pizza [name=" + name + ", dough=" + dough + ", sauce=" + sauce + ", toppings=" + toppings + "]";
	}
	
}
