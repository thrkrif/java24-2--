package decorator;

public class HatDecorator extends DollDecorator{

    public HatDecorator(DollDecorator decoratedDoll){
        this.doll = decoratedDoll;
    }

    @Override
    public String decribe() {
       return image.describe() + "Hat";
    }

    @Override
    public void paintComponent(Graphics g) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'paintComponent'");
    }



}
