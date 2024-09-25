package LSP;

public class EBook extends Book{


    public EBook(String title, String author) {
        super(title,author);   // 부모 생성자 호출
        //TODO Auto-generated constructor stub
    }

    @Override
    public void generateReport(){
        EBookgenerateReport();
    }

    public void EBookgenerateReport(){
        System.out.print("Title: " +  this.title + ", author: " + this.author);
        download();
    }
    
    public void download(){
        System.out.println(", download: True");
    }
}
