package HW1;

public class ElectricCar extends Car implements Rechargeable{
    
    private String recharge;
    public ElectricCar(String name,String recharge){
        super(name);
        this.recharge = recharge;
    }

    // Report 인터페이스 구현
    @Override
    public void report() {
        super.report();
        recharge();
    }

    // Rechargeable 인터페이스 구현
    @Override
    public void recharge(){
        ElectricCarRecharge();
    }

    public void ElectricCarRecharge(){
        System.out.println(", recharge: " + this.recharge);
    }

}
