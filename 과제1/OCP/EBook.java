package OCP;

public class EBook extends Book{


    public EBook(String title, String author) {
        super(title,author);   // 부모 생성자 호출
        //TODO Auto-generated constructor stub
    }

    // 메서드 오버라이딩
    @Override
    public void generateReport(){
        generateReportEBook();
        download();
    }
    
    public void generateReportEBook(){
        System.out.print("Title: " + this.title + ", author: " + this.author);
    }

    
    public void download(){
        System.out.println(", download: True");
    }
}
