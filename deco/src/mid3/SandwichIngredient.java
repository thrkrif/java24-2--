package mid3;
public abstract class SandwichIngredient implements Ingredient{
    // 데코레이터로 컴포넌트의 객체 참조
    // Sandwich decoratedSandwich;

    // public SandwichIngredient(Sandwich decoratedSandwich){
    //     this.decoratedSandwich = decoratedSandwich;
    // }

    // @Override
    // public String getDescription(){
    //     return "start decorated sandwich";
    // }

    // cost는 밑에서 오버라이드

    // -> 위처럼 작성을 하면 sandwich에만 데코레이터 적용 가능(concrete component를 참조한거임)
    // Ingredient 인터페이스를 참조하면 하위 여러개의 concrete component를 사용 가능하다

    protected Ingredient ingredient;

    public SandwichIngredient(Ingredient ingredient){
        this.ingredient = ingredient;
    }

    @Override
    public String getDescription(){
        return ingredient.getDescription();
    }

    @Override
    public double cost(){
        return ingredient.cost();
    }

}
