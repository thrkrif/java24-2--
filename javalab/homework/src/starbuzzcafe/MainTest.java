package starbuzzcafe;

public class MainTest {
    public MainTest(){
        Beverage b = new DeCaf();
        System.out.println(b.getDescription() + " $" + b.cost());

        b = new Milk(b);
        System.out.println(b.getDescription() + " $" + b.cost());


        // DarkRoast + Milk + Whip
        b = new Whip(new Milk(new DarkRoast()));
        System.out.println(b.getDescription() + " $" + b.cost());
    }
}
