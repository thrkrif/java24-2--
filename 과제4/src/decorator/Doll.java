package decorator;

// component
public abstract class Doll extends JPanel implements IDoll {
    // 이미지를 저장 할 image
    private Image image;

   
    @Override
    public abstract void paintComponent(Graphics g);
   
}
