package starbuzzcafe;

public abstract class CondimentDecorator extends Beverage{
    protected Beverage decoratedBeverage;

    protected CondimentDecorator(Beverage decoratedBeverage){
        this.decoratedBeverage = decoratedBeverage;
    }

    public abstract String getDescription();
}
