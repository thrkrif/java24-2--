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
        super.paintComponent(g); // 기본 인형을 그린 후 커파를 그림
        Graphics2D g2 = (Graphics2D) g;
        if (image != null) {
            g2.drawImage(image, 450, 500, 100, 100, null); // 커피 위치 및 크기 지정
        }
    }

    

}
