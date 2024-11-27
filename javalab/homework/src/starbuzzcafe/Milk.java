package starbuzzcafe;

public class Milk extends CondimentDecorator{
    
    public Milk(Beverage decoratedBeverage){
        super(decoratedBeverage);
    }

    @Override
    public String getDescription() {
        return this.decoratedBeverage.getDescription() + " with a Milk";
    }

    @Override
    public double cost() {
        return this.decoratedBeverage.cost() + 0.39;
    }
}
