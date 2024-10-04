package decorator;

public class CatDoll extends Doll{

    public CatDoll(){
        // 해당 이미지를 설정한다.
    }

    @Override
    public String decribe() {
        // 각각의 정보를 추가해서 반환한다.
       System.out.println("Cat");
    }

    @Override
    public void paintComponent(Graphics g) {
        // 각 이미지를 추가해서 화면에 그린다.
    }

}
