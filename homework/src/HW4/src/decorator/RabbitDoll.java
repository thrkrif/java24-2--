package HW4.src.decorator;

import javax.swing.*;
import java.awt.*;

public class RabbitDoll extends Doll{

    public RabbitDoll() {
        this.image = new ImageIcon("image2/rabbit.png").getImage(); // 토끼 이미지 불러오기
    }

    @Override
    public String describe() {
        return "Rabbit Doll";
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // 부모 클래스의 paintComponent 호출
        if (image != null) {
            g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), this); // 토끼 이미지를 그림
        }
    }

    
}
