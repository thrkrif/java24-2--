package factory;

public class MainTest {
    public MainTest(){
        PizzaStore chicagoStore = new ChicagoPizzaStore();
        chicagoStore.orderPizza("cheese");
        PizzaStore chicagoStore2 = new ChicagoPizzaStore();
        chicagoStore2.orderPizza("pepperoni");
    }
}
