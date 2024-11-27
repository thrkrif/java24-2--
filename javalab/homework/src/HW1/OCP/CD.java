package HW1.OCP;

public class CD extends LibraryItem{
    private String artist;


    public CD(String title, String artist) {
        this.title = title;   // 부모 생성자 호출
        this.artist = artist;
        //TODO Auto-generated constructor stub
    }

    @Override
    public void generateReport(){
        generateReportCD();
    }
    
    public void generateReportCD(){
        System.out.println("Title: " + this.title + ", artist: " + this.artist);
    }
}
