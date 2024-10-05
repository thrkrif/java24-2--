package shapeDecorator;

public abstract class Shape {
    public void draw(){
        // 하위 클래스에서 getDescription 구현하면 자동으로 구현됨
        System.out.println("draw " + getDescription());
    }
    // 추상메서드 선언
    public abstract String getDescription();
}
