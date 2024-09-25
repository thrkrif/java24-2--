package ISP;

public class CD extends LibraryItem{
    private String artist;


    public CD(String title, String artist) {
        super(title);   // 부모 생성자 호출
        this.artist = artist;
        //TODO Auto-generated constructor stub
    }

    // 메서드 오버라이딩
    @Override
    public void generateReport(){
        super.generateReport(); // 부모 메서드 호출
        System.out.println(", artist: " + this.artist);
    }
}
