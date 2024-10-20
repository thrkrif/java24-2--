package factory;

public class ChicagoPizzaStore extends PizzaStore{
    
    
    // create 구현
    @Override
    public Pizza createPizza(String type){
        if(type.equals("cheese")){
            return new ChicagoCheesePizza();
        }
        else if(type.equals("pepperoni")){
            return new ChicagoPepperoniPizza();
        }
        return null;   // 조건에 맞는게 없으면 null 반환
    }
}
