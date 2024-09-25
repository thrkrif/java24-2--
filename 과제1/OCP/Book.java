package OCP;

public class Book extends LibraryItem{
    protected String author;


    public Book(String title, String author) {
        this.title = title;
        this.author = author;
        //TODO Auto-generated constructor stub
    }

    @Override
    public void generateReport(){
        generateReportBook();
    }

    public void generateReportBook(){
        System.out.println("Title: " + this.title + ", author: " + this.author);
    }
}
