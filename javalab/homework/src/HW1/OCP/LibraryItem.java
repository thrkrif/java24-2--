package HW1.OCP;
// Using polymorphism
// 추상 클래스로 만들어 중복되는 멤버, 메서드를 통합하였다.
// 모든 하위 클래스 멤버에게는 title이 있을 것이다.
// 모든 하위 클래스 멤버는 정보를 출력하는 메서드가 필요하다.
public abstract class LibraryItem{
    protected String title;
    public abstract void generateReport();
}
