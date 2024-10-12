package HW4.src.decorator;

import javax.swing.*;
import java.awt.*;

public class HatDecorator extends DollDecorator{

    public HatDecorator(Doll decoratedDoll){
        super(decoratedDoll);
        this.image = new ImageIcon("image/hat.png").getImage(); // 모자 이미지 로드
    }

    @Override
    public String describe() {
       return this.decoratedDoll.describe() + " with a Hat";
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        if (image != null) {
            g2.drawImage(image, 20, -150, 500, 500, null); // 모자 위치 및 크기 설정
        }
    }



}
