package pizzaAbstractFactory;

import java.util.Arrays;

public abstract class Pizza {
	String name;
	Dough dough;
	Sauce sauce;
	Cheese cheese;
	Veggies[] veggies;
	Pepperoni pepperoni;
	Clams clam;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public abstract void prepare();
	
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
		return "Pizza [name=" + name + ", dough=" + dough + ", sauce=" + sauce + ", cheese=" + cheese + ", veggies="
				+ Arrays.toString(veggies) + ", pepperoni=" + pepperoni + ", clam=" + clam + "]";
	}
	
}
