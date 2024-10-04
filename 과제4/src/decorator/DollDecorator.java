package decorator;
// Decorator
// contains a reference to the component object
public abstract class DollDecorator extends Doll{
    // component object
    private Doll decoratedDoll;
    
    public DollDecorator(Doll decorateDoll){
        this.decoratedDoll = decorateDoll;
    }

    @Override
    public String describe(){
        return decoratedDoll.decribe();
    }

    public abstract void paintComponent(Graphics g);

}
