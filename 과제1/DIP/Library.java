package DIP;
import java.util.ArrayList;
import java.util.List;

public class Library{
    private List<LibraryItem> items;
    private CheckoutManager checkoutManager;

    public Library() {
        this.items = new ArrayList<>();
        this.checkoutManager = new CheckoutManager();
    }

    public void addItem(LibraryItem item) {
        items.add(item);
    }

    public void checkOutItem(LibraryItem item) {
        checkoutManager.checkOut(item);
    }

    public void returnItem(LibraryItem item) {
        checkoutManager.returnItem(item);
    }

    // 도서 목록과 현재 도서 대출의 상태를 출력한다.
    public void generateReport() {
        System.out.println("Library generateReport");
        for (LibraryItem item : items) {
            item.generateReport();
            System.out.println(", checkoutStatus: " + checkoutManager.isCheckedOut(item));
        }
    }

}
