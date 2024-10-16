package shape;

public class FactoryMainTest {

	public FactoryMainTest() {
		// get color factory
		AbstractFactory<ShapeColor> colorFactory = FactoryProvider.getFactory("COLOR");
		System.out.println(colorFactory);
		ShapeColor c1 = colorFactory.create("RED");
		c1.color();
		ShapeColor c2 = colorFactory.create("GREEN");
		c2.color();
		ShapeColor c3 = colorFactory.create("BLUE");
		c3.color();

		// get shape factory
		AbstractFactory<Shape> shapeFactory = FactoryProvider.getFactory("SHAPE");
		System.out.println(shapeFactory);
		Shape s1 = shapeFactory.create("RECTANGLE");
		s1.draw();
		Shape s2 = shapeFactory.create("CIRCLE");
		s2.draw();
		Shape s3 = shapeFactory.create("TRIANGLE");
		s3.draw();

		// get button factory
		AbstractFactory<Button> buttonFactory = FactoryProvider.getFactory("BUTTON");
		System.out.println(buttonFactory);
		Button b1 = buttonFactory.create("PUSHBUTTON");
		b1.paint();
		Button b2 = buttonFactory.create("TOGGLEBUTTON");
		b2.paint();
		
		// ShapeFactory.getShape
		Shape shape1 = ShapeFactory.getShape(ShapeType.RECTANGLE);
		shape1.draw();
		Shape shape2 = ShapeFactory.getShape(ShapeType.CIRCLE);
		shape2.draw();
		Shape shape3 = ShapeFactory.getShape(ShapeType.TRIANGLE);
		shape3.draw();
	}
	
}
