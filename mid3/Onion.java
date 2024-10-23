package mid3;

public class Onion extends SandwichIngredient{
    private String name = "onion";
    public Onion(Ingredient ingredient){
        super(ingredient);
    }

    @Override
    public String getDescription(){
        return super.getDescription() + " with a " + name;
    }

    @Override
    public double cost(){
        return ingredient.cost() + 0.2;
    }
    
}

