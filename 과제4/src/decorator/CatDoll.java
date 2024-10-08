package decorator;

import javax.swing.*;
import java.awt.*;

public class CatDoll extends Doll{

    public CatDoll() {
        this.image = new ImageIcon("image/cat.png").getImage(); // 고양이 이미지 불러오기
    }

    @Override
    public String describe() {
        return "Cat Doll";
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // 부모 클래스의 paintComponent 호출
        if (image != null) {
            g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), this); // 고양이 이미지를 그림
        }
    }

    
}
