package starbuzzcafe;

public class DeCaf extends Beverage{
    public DeCaf(){
        description = "DeCaf";
    }

    @Override
    public double cost() {
        return 0.59;
    }

}