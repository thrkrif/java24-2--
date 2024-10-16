package pizzaAbstractFactory;

public class ClamPizza extends Pizza {
	PizzaIngredientFactory ingredientFactory;
	
	public ClamPizza(PizzaIngredientFactory ingredientFactory) {
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
		clam = ingredientFactory.createClam();
		System.out.println(clam);
	}

}
