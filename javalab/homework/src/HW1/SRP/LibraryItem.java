package HW1.SRP;
public class LibraryItem implements ReportGenerator{
    private String title;

    public LibraryItem(String title) {
        this.title = title;
    }

    // ReportGenerator 인터페이스의 구현, 도서 목록을 출력한다.
    @Override
    public void generateReport() {
        System.out.print("Title: " + title);
        
    }

}
