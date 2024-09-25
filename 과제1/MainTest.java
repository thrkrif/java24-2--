package ISP;

public class MainTest {
    // ISP - Interface Segregation Principle
	// 한 클래스는 자신이 사용하지않는 인터페이스는 구현하지 말아야 한다. 
	// 하나의 일반적인 인터페이스보다 여러개의 구체적인 인터페이스가 낫다.
    public MainTest() {
        Car[] items1 = { 
            new GasCar("GV80", "Gasolin"),
            new ElectricCar("Tesla", "Electric")
        };

        for (Car item : items1) {
            item.report();
        }

        
    }
}
