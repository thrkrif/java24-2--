package HW1.DIP;

public class Book extends LibraryItem{
    private String author;


    public Book(String title, String author) {
        super(title);   // 부모 생성자 호출
        this.author = author;
        //TODO Auto-generated constructor stub
    }

    // 메서드 오버라이딩
    @Override
    public void generateReport(){
        super.generateReport(); // 부모 메서드 호출
        System.out.println(", author: " + this.author);
    }
}
