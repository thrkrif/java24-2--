package factory;

public abstract class ChicagoStylePizza extends Pizza{
    public ChicagoStylePizza(){
        //시카고 스타일 피자의 공통 특징
        dough = "Extra Thick Crust Dough";
		sauce = "Plum Tomato Sauce";
    }

    @Override
	public void cut() {
		System.out.println("Cutting the pizza into square slices.");
	}
}
