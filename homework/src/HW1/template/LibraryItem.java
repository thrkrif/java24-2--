package HW1.template;
public class LibraryItem{
    private String title;
    private String author;
    private String type; // "Book", "CD", "DVD", "Ebook"
    private boolean isCheckedOut;

    public LibraryItem(String title, String author, String type) {
        this.title = title;
        this.author = author;
        this.type = type;
        this.isCheckedOut = false;
    }

    public void checkOut() {
        isCheckedOut = true;
    }

    public void returnItem() {
        isCheckedOut = false;
    }

    public void generateReport() {
        System.out.println("Title: " + title + ", Author: " + author + ", Type: " + type + ", Checked Out: " + isCheckedOut);
    }
}
