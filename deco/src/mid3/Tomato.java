package mid3;

public class Tomato extends SandwichIngredient{
    private String name = "tomato";

    public Tomato(Ingredient ingredient){
        super(ingredient);
    }

    @Override
    public String getDescription(){
        return ingredient.getDescription() + " with a " + name;
    }

    @Override
    public double cost(){
        return ingredient.cost() + 0.1;
    }
}
