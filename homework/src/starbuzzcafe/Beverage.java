package starbuzzcafe;

public abstract class Beverage {
    String description = "beverage";

    public String getDescription(){
        return description;
    }

    public abstract double cost();
    
}
