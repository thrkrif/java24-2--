package ISP;

public class GasCar extends Car implements Refuelable{

    private String refuel;
    public GasCar(String name,String refuel){
        super(name);
        this.refuel = refuel;
    }

    // Report 인터페이스 구현
    @Override
    public void report() {
        super.report();
        refuel();
    }

    // Refuelable 인터페이스 구현
    @Override
    public void refuel(){
        GasCarRefuel();
    }

    public void GasCarRefuel(){
        System.out.println(", refuel: " + this.refuel);
    }
}
