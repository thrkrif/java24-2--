package eagerInitialization;

public class Singleton {

	// static eager initialization
	private static Singleton instance = new Singleton();
	
	private Singleton() {
		System.out.println("Thread-Safe Singleton (Eager Initialization) constructor");
	}
	
	public static Singleton getInstance() {
		return instance;
	}
	
	public void print() {
		System.out.println("Thread-Safe Singleton (Eager Initialization) instance hashCode=" + instance.hashCode());
	}
}
