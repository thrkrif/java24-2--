package staticBlock;

public class Singleton {
	private static Singleton instance;
	
	// static block
	static {
		try {
			instance = new Singleton();
			System.out.println("StaticBlockSingletonPattern static initializer block hashCode=" + instance.hashCode());
		} catch(Exception e) {
			throw new RuntimeException("Error creating singleton instance");
		}
	}
	
	private Singleton() {
		System.out.println("StaticBlockSingletonPattern constructor");
	}
	
	public static Singleton getInstance() {
		return instance;
	}
	
	public void print() {
		System.out.println("StaticBlockSingletonPattern instance hashCode=" + instance.hashCode());
	}
}
