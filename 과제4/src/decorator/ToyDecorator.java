package decorator;

import javax.swing.*;
import java.awt.*;

public class ToyDecorator extends DollDecorator{

    public ToyDecorator(Doll decoratedDoll) {
        super(decoratedDoll);
        this.image = new ImageIcon("image/toy.png").getImage(); // 장난감 이미지 로드
    }

    @Override
    public String describe() {
        return decoratedDoll.describe() + " with a Toy";
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);  // 기본 인형을 그린 후 장난감을 그림
        Graphics2D g2 = (Graphics2D) g;
        if (image != null) {
            g2.drawImage(image, 300, 300, 400, 400, null);  // 장난감 위치 지정
        }
    }

    


}
