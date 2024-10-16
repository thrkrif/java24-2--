package pizzaAbstractFactory;

import java.util.Arrays;

public class VeggiePizza extends Pizza {
	PizzaIngredientFactory ingredientFactory;
	
	public VeggiePizza(PizzaIngredientFactory ingredientFactory) {
		this.ingredientFactory = ingredientFactory;
	}

	@Override
	public void prepare() {
		System.out.println("Preparing " + name);
		dough = ingredientFactory.createDough();
		System.out.println(dough);
		sauce = ingredientFactory.createSauce();
		System.out.println(sauce);
		cheese = ingredientFactory.createCheese();
		System.out.println(cheese);
		veggies = ingredientFactory.createVeggies();
		System.out.println(Arrays.deepToString(veggies));
	}

}
