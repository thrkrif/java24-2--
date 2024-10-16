package sandwich;

import java.util.Arrays;

public class SandwichBuilderMainTest {
	public SandwichBuilderMainTest() {
		// classic way to create an object

		// Sandwich s = new Sandwich();
		// s.setName("Steak And Cheese Sandwich");
		// s.setBread("Oatmeal Bread");
		// s.setPatty("Beef Steak");
		// s.setPrice(10500);
		// s.setOthers(Arrays.asList("Cheese", "Tomato", "Onion"));
		// System.out.println(s);
		
		// builder pattern
		Sandwich s = new Sandwich.Builder()
				.name("Chicken Bacon Avocado Sandwich")
				.build();
				System.out.println(s);
		
		Sandwich s1 = new Sandwich.Builder()
				.bread("Whole Grain Bread")
				.patty("Chicken")
				.price(9500)
				.others(Arrays.asList("Bacon","Avocado"))
				.build();
		System.out.println(s1);
	}
}
