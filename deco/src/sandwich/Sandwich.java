package sandwich;
public class Sandwich implements Ingredient{
    // concrete component

    @Override
    public String getDescription(){
        return "basic sandwich";
    }

    @Override
    public double cost(){
        return 0.5; // 기본 샌드위치의 가격
    }
}
