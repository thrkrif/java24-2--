package HW1.LSP;

public class Book extends LibraryItem{
    protected String author;


    public Book(String title, String author) {
        super(title);   // 부모 생성자 호출
        this.author = author;
        //TODO Auto-generated constructor stub
    }

    @Override
    public void generateReport(){
        super.generateReport();
        BookgenerateReport();
    }

    public void BookgenerateReport(){
        System.out.println(", author: " + this.author); 
    }


}
