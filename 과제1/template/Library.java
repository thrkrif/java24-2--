package template;
import java.util.ArrayList;
import java.util.List;

public class Library {
    private List<LibraryItem> items;

    public Library() {
        this.items = new ArrayList<>();
    }

    public void addItem(LibraryItem item) {
        items.add(item);
    }

    public void checkOutItem(LibraryItem item) {
        item.checkOut();
    }

    public void returnItem(LibraryItem item) {
        item.returnItem();
    }

    public void generateReport() {
        System.out.println("Library generateReport");
        for (LibraryItem item : items) {
            item.generateReport();
        }
    }
}
