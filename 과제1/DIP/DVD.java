package DIP;

public class DVD extends LibraryItem implements Streamable{
    private String director;
    private int duration;


    public DVD(String title, String director, int duration) {
        super(title);   // 부모 생성자 호출
        this.director = director;
        this.duration = duration;
        //TODO Auto-generated constructor stub
    }

    // 메서드 오버라이딩
    @Override
    public void generateReport(){
        super.generateReport(); // 부모 메서드 호출
        System.out.print(", director: " + this.director + ", duration: " + this.duration);
        stream();
    }
    // 인터페이스 구현, pdf에 멤버 변수가 설정 되어 있지 않아 따로 작성하지 않았음.
    @Override
    public void stream(){
        System.out.println(", stream: True");
    }
}
