package threadSafe;

public class Singleton {

	private static Singleton instance = null;
	
	private Singleton() {
		System.out.println("Thread-Safe Singleton constructor");
	}
	
	// thread-safe using synchronized
	public synchronized static Singleton getInstance() {
		if (instance == null)
			instance = new Singleton();
		return instance;
	}
	
	public void print() {
		System.out.println("Thread-Safe using synchronized Singleton instance hashCode=" + instance.hashCode());
	}
}
