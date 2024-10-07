package decorator;

import javax.swing.*;
import java.awt.*;

public class CoffeeDecorator extends DollDecorator{

    public CoffeeDecorator(Doll decoratedDoll) {
        super(decoratedDoll);
        this.image = new ImageIcon("image2/coffee.png").getImage(); // 커피 이미지 로드
    }

    @Override
    public String describe() {
        return decoratedDoll.describe() + " with a Coffee";
    }


    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g); // 기본 인형을 그린 후 공을 그림
        Graphics2D g2 = (Graphics2D) g;
        if (image != null) {
            // 크기를 줄여서 그리기 (50x50 픽셀)
            g2.drawImage(image, 450, 500, 100, 100, null); // 공 위치 및 크기 지정
        }
    }

    

}
