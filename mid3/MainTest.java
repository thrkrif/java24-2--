package mid3;
public class MainTest {
    public MainTest(){
    Recipe recipe = new ColdRecipe(); // coldRecipe 생성

    Ingredient sandwich = new AmericanCheese(new Sandwich(recipe));
    
    System.out.println(sandwich.getDescription() + ", $" + sandwich.cost());
    
    sandwich = OtherIngredientFactory.getInstance("lettuce", sandwich);
    sandwich = OtherIngredientFactory.getInstance("onion", sandwich);
    sandwich = OtherIngredientFactory.getInstance("tomato", sandwich);

    // 데코레이터도 사용 가능
    sandwich = new Tomato(sandwich);

    System.out.println(sandwich.getDescription() + " 비용: $" + sandwich.cost());
    }
}
