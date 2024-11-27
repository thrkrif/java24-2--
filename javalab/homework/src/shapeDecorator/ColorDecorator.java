package shapeDecorator;

public class ColorDecorator extends ShapeDecorator{
   private String color;

   public ColorDecorator(String color, Shape shape){
    super(shape);    
    this.color = color;
   }

   @Override
   public String getDescription(){
    return this.shape.getDescription() + " With " + color + " Color";
   }
    
}
