package factory;

public abstract class PizzaStore {
    // 피자 객체를 생성하는곳은 하위 구체 클래스들
    public abstract Pizza createPizza(String type);


    public Pizza orderPizza(String type){
        Pizza pizza = createPizza(type);
        System.out.println("---- Making a " + pizza.getName() + " ----");
		pizza.prepare();
		pizza.bake();
		pizza.cut();
		pizza.box();
        return pizza;
    }
}
