package OCP;

public class DVD extends LibraryItem{
    private String director;
    private int duration;


    public DVD(String title, String director, int duration) {
        this.title = title;   // 부모 생성자 호출
        this.director = director;
        this.duration = duration;
        //TODO Auto-generated constructor stub
    }

    @Override
    public void generateReport(){
        generateReportDVD();
        stream();
    }

    public void generateReportDVD(){
        System.out.print("Title: " + this.title + ", director: " + this.director + ", duration: " + this.duration);
    }
    
    public void stream(){
        System.out.println(", stream: True");
    }
}
