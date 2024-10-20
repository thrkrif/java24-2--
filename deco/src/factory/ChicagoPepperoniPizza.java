package factory;

public class ChicagoPepperoniPizza extends ChicagoStylePizza{
    public ChicagoPepperoniPizza(){
        super();    // 공통 특징인 도우와 소스 필드
        name = "Chicago Style Pepperoni Pizza";
		toppings.add("Shredded Mozzarella Cheese");
		toppings.add("Slices Pepperoni");
		toppings.add("Garlic");
		toppings.add("Onion");
    }

}
