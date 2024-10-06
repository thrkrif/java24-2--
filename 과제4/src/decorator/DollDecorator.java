package decorator;

import java.awt.Graphics;


// Decorator
// contains a reference to the component object
public abstract class DollDecorator extends Doll{
    // component object
    protected Doll decoratedDoll;
    
    protected DollDecorator(Doll decorateDoll){
        this.decoratedDoll = decorateDoll;
    }

    @Override
    public abstract String describe();

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (decoratedDoll != null) {
            decoratedDoll.paintComponent(g); // 장식된 인형 그리기
        }
    }
}
