package DIP;

public class EBook extends LibraryItem implements Downloadable{
    private String artist;


    public EBook(String title, String artist) {
        super(title);   // 부모 생성자 호출
        this.artist = artist;
        //TODO Auto-generated constructor stub
    }

    // 메서드 오버라이딩
    @Override
    public void generateReport(){
        super.generateReport(); // 부모 메서드 호출
        System.out.print(", artist: " + this.artist);
        download();
    }
    // 인터페이스 구현
    @Override
    public void download(){
        System.out.println(", download: True");
    }
}
