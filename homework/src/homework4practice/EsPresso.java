package homework4practice;

public class EsPresso extends Beverage{
    public EsPresso(){
        description = "EsPresso";
    }

    @Override
    public double cost() {
        return 0.49;
    }

}
