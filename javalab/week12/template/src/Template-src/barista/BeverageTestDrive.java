package barista;

public class BeverageTestDrive {

	public BeverageTestDrive() {
		Tea tea = new Tea();
		Coffee coffee = new Coffee();
		
		System.out.println("Making tea...");
		tea.prepareRecipe();
		
		System.out.println("Making coffee...");
		coffee.prepareRecipe();
		
		TeaWithHook tea2 = new TeaWithHook();
		CoffeeWithHook coffee2 = new CoffeeWithHook();
		
		System.out.println("Making teaWithHook...");
		tea2.prepareRecipe();
		
		System.out.println("Making coffeeWithHook...");
		coffee2.prepareRecipe();
	}

}
