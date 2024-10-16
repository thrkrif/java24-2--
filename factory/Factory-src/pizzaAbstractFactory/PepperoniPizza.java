package pizzaAbstractFactory;

import java.util.Arrays;

public class PepperoniPizza extends Pizza {
	PizzaIngredientFactory ingredientFactory;
	
	public PepperoniPizza(PizzaIngredientFactory ingredientFactory) {
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
		pepperoni = ingredientFactory.createPepperoni();
		System.out.println(pepperoni);
	}

}
