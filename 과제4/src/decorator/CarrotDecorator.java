package decorator;

import javax.swing.*;
import java.awt.*;

public class CarrotDecorator extends DollDecorator{

    public CarrotDecorator(Doll decoratedDoll) {
        super(decoratedDoll);
        this.image = new ImageIcon("image2/carrot.png").getImage(); // 당근 이미지 로드
    }

    @Override
    public String describe() {
        return decoratedDoll.describe() + " with a Carrot";
    }


    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g); // 기본 인형을 그린 후 공을 그림
        Graphics2D g2 = (Graphics2D) g;
        if (image != null) {
            // 크기를 줄여서 그리기 (50x50 픽셀)
            g2.drawImage(image, 10, 250, 300, 300, null); // 공 위치 및 크기 지정
        }
    }

    

}
