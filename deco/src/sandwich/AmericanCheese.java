package sandwich;
public class AmericanCheese extends SandwichIngredient{
    // concrete Decorator
    private String name = "AmericanCheese";
    public AmericanCheese(Ingredient ingredient){
        super(ingredient);
    }

    @Override
    public String getDescription(){
        return super.getDescription() + ", " + name;
    }

    @Override
    public double cost(){
        return ingredient.cost() + 0.3;
    }
    
}
