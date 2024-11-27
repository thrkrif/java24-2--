package shapeDecorator;

public class Rectangle extends Shape{
    // 추상 클래스 아니기 때문에 추상메서드 구현해줘야함

    @Override
    public String getDescription(){
        return "Rectangle";
    } 

}
