package LSP;

public class MainTest {
    // LSP - Liskov Substitution Principle
	// 자식 클래스는 언제나 자신의 부모 클래스를 대체할 수 있어야한다. 즉 부모 클래스가 들어갈 자리에 자식 클래스를 넣어도 계획대로 잘 작동해야 한다.
	// 자식클래스는 부모클래스의 책임을 무시하거나 재정의하지 않고 확장만 수행하도록 해야 LSP를 만족한다.
    //업캐스팅 해도 문제 없도록 하는것이다. → 업캐스팅 상태에서 부모 메서드를 이용해도 문제가 없어야한다.
    public MainTest() {
        
        LibraryItem[] items1 = { 
            new Book("The Little Prince", "Antoine Marie Jean-Baptiste Roger de Saint-Exupéry"),
            new CD("We Are the World", "Roger Emerson, Michael Jackson, Lionel Richie"),
            new DVD("Star Wars: A New Hope", "George Lucas", 120),
            new EBook("Onepiece", "EIICHIRO ODA")
        };
        
        for (LibraryItem item : items1) {
            item.generateReport(); // upcasting
        }

    }
}
