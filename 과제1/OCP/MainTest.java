package OCP;

public class MainTest {
    public MainTest() {
        LibraryItem[] items1 = { 
            new Book("The Little Prince", "Antoine Marie Jean-Baptiste Roger de Saint-Exupéry"),
            new CD("We Are the World", "Roger Emerson, Michael Jackson, Lionel Richie"),
            new DVD("Star Wars: A New Hope", "George Lucas", 120),
            new EBook("Onepiece", "EIICHIRO ODA")
        };

        for (LibraryItem item : items1) {
            item.generateReport();
        }

    }
}
