package mid3;

public class OtherIngredientFactory {
    // 팩토리에서는 객체를 만든다.
    public static Ingredient getInstance(String name, Ingredient ingredient){
        if (name.equals("lettuce")){
            ingredient = new Lettuce(ingredient);
            return ingredient;
        }
        else if (name.equals("tomato")){
            ingredient = new Tomato(ingredient);
            return ingredient;
        }
        else if (name.equals("onion")){
            ingredient = new Onion(ingredient);
            return ingredient;
        }
        return null;

    }
}
