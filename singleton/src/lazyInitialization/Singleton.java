package lazyInitialization; 

// Thread-Safe Singleton (Lazy Initialization with Double Checked Locking)
public class Singleton {

	// Double Checked Locking (volatile since Java5)
	private static volatile Singleton instance = null;
	
	private Singleton() {
		System.out.println("Thread-Safe Singleton (Lazy Initialization with Double Checked Locking) constructor");
	}
	
	public static Singleton getInstance() {
		if (instance == null) {
			synchronized (Singleton.class) {
				if (instance == null)
					instance = new Singleton();				
			}
		}
		return instance;
	}
	
	public void print() {
		System.out.println("Thread-Safe Singleton (Lazy Initialization with Double Checked Locking) instance hashCode=" + instance.hashCode());
	}
}
