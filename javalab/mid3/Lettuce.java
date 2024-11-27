package mid3;

public class Lettuce extends SandwichIngredient{
    private String name = "lettuce";
    public Lettuce(Ingredient ingredient){
        super(ingredient);
    }

    @Override
    public String getDescription(){
        return super.getDescription() + " with a " + name;
    }

    @Override
    public double cost(){
        return ingredient.cost() + 0.1;
    }
}
