package shapeDecorator;

public abstract class ShapeDecorator extends Shape{
    // getDescription 구현 해도 되고 안해도 됨
    // 하위에서 구현하면 될 듯
    protected Shape shape;

    protected ShapeDecorator(Shape shape){
        this.shape = shape;
    }
    
}
