package shape;

public class ShapeFactory implements AbstractFactory<Shape> {

	@Override
	public String toString() {
		return "ShapeFactory []";
	}

	public static Shape getShape(ShapeType type) {
		switch(type) {
		case RECTANGLE:
			return new Rectangle();         
		case TRIANGLE:
			return new Triangle();	 
		case CIRCLE:
			return new Circle();	 
		default:
			return null;
		}
	}

	@Override
	public Shape create(String type) {
		switch(type) {
		case "RECTANGLE":
			return new Rectangle();         
		case "TRIANGLE":
			return new Triangle();	 
		case "CIRCLE":
			return new Circle();	 
		default:
			return null;
		}
	}

}
