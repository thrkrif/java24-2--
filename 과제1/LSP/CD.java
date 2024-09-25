package LSP;

public class CD extends LibraryItem{
    private String artist;


    public CD(String title, String artist) {
        super(title);   // 부모 생성자 호출
        this.artist = artist;
        //TODO Auto-generated constructor stub
    }

    @Override
    public void generateReport(){
        super.generateReport();
        CDgenerateReport();
    }

    public void CDgenerateReport(){
        System.out.println(", artist: " + this.artist);
    }
}
