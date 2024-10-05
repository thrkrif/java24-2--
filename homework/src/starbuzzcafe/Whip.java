package starbuzzcafe;

public class Whip extends CondimentDecorator{

    public Whip(Beverage decoratBeverage){
        super(decoratBeverage);
    }

    @Override
    public String getDescription(){
        return decoratedBeverage.getDescription() + " with a Whip";
    }

    @Override
    public double cost(){
        return decoratedBeverage.cost() + 0.29;
    }


}
