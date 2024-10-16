package shape;

public class FactoryProvider {
	public static AbstractFactory getFactory(String choice) {
		if (choice.equalsIgnoreCase("COLOR")) 
			return new ShapeColorFactory();
		else if (choice.equalsIgnoreCase("SHAPE")) 
			return new ShapeFactory();
		else if (choice.equalsIgnoreCase("BUTTON")) 
			return new ButtonFactory();
		else 
			return null;
	}
}
