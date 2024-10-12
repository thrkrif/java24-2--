package HW1.ISP;

public class MainTest {
    // ISP - Interface Segregation Principle
	// 한 클래스는 자신이 사용하지않는 인터페이스는 구현하지 말아야 한다. 
	// 하나의 일반적인 인터페이스보다 여러개의 구체적인 인터페이스가 낫다.
    public MainTest() {
        LibraryItem[] items1 = { 
            new Book("The Little Prince", "Antoine Marie Jean-Baptiste Roger de Saint-Exupéry"),
            new CD("We Are the World", "Roger Emerson, Michael Jackson, Lionel Richie"),
            new DVD("Star Wars: A New Hope", "George Lucas", 120),
            new EBook("Onepiece", "EIICHIRO ODA")
            // new LibraryItem("The Little Prince", "Antoine Marie Jean-Baptiste Roger de Saint-Exupéry", "Book"),
            // new LibraryItem("We Are the World", "Roger Emerson, Michael Jackson, Lionel Richie", "CD"),
            // new LibraryItem("Star Wars: A New Hope", "George Lucas", "DVD"),
            // new LibraryItem("Onepiece", "EIICHIRO ODA", "Ebook")
        };

        for (LibraryItem item : items1) {
            item.generateReport();
        }

        
    }
}
