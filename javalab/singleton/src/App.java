
public class App {

	public static void main(String[] args) {
		new chocolate.ChocolateController();
		new classic.SingletonPatternTest();
		new threadSafe.SingletonPatternTest();
		new eagerInitialization.SingletonPatternTest();
		new lazyInitialization.SingletonPatternTest();
		new innerStaticClass.SingletonPatternTest();
		new staticBlock.SingletonPatternTest();
	}

}
