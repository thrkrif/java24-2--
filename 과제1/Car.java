package ISP;

public class Car implements Report{

    private String name;    // 차의 이름

    public Car(String name){
        this.name = name;
    }

    // 인터페이스 구현
    @Override
    public void report() {
        System.out.print("Name: " + name);
    }

}
