package classic;

public class Singleton {

	private static Singleton instance = null;
	
	private Singleton() {
		System.out.println("Classic Singleton constructor");
	}
	
	public static Singleton getInstance() {
		if (instance == null)
			instance = new Singleton();
		return instance;
	}
	
	// NOTE: This is not thread safe!
	public void print() {
		System.out.println("Classic Singleton instance hashCode=" + instance.hashCode());
	}
}
