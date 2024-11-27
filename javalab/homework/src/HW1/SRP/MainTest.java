package HW1.SRP;

public class MainTest {

    public MainTest() {
        Library library1 = new Library();
        LibraryItem[] items1 = { 
            new LibraryItem("The Little Prince"),
            new LibraryItem("We Are the World"),
            new LibraryItem("Star Wars: A New Hope"),
            new LibraryItem("Onepiece")
        };
        for (var item : items1) {
            library1.addItem(item);
        }
        library1.checkOutItem(items1[0]);   // 대출 가능
        library1.checkOutItem(items1[1]);   // 대출 가능
        library1.generateReport();
        library1.returnItem(items1[1]);     // 대출 불가
        library1.generateReport();
    }
}
