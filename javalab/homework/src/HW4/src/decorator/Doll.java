package HW4.src.decorator;

import javax.swing.*;
import java.awt.*;

// component
public abstract class Doll extends JPanel implements IDoll {
    // 이미지를 저장 할 image
    protected Image image;

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // 기본 동작 호출
        if (image != null) {
            g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), this);
        }
    }

}
