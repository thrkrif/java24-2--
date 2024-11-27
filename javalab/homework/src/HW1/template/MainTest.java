package HW1.template;

public class MainTest {

    public MainTest() {
        Library library1 = new Library();
        LibraryItem[] items1 = { 
            new LibraryItem("The Little Prince", "Antoine Marie Jean-Baptiste Roger de Saint-Exupéry", "Book"),
            new LibraryItem("We Are the World", "Roger Emerson, Michael Jackson, Lionel Richie", "CD"),
            new LibraryItem("Star Wars: A New Hope", "George Lucas", "DVD"),
            new LibraryItem("Onepiece", "EIICHIRO ODA", "Ebook")
        };
        for (var item : items1) {
            library1.addItem(item);
        }
        library1.checkOutItem(items1[0]);
        library1.checkOutItem(items1[1]);
        library1.generateReport();
        library1.returnItem(items1[1]);
        library1.generateReport();

        // 레포지토리 제대로 추가된 거 맞아?
        
    }
}
