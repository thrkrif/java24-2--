package innerStaticClass;

public class Singleton {
	private Singleton() {
		System.out.println("InitializationOnDemandSingletonPattern constructor");
	}

	// Thread-safe using inner static class LazyHolder
	private static class SingletonHolder {
		private static final Singleton instance = new Singleton();
	}
	
	public static Singleton getInstance() {
		return SingletonHolder.instance;
	}
	
	public void print() {
		System.out.println("InitializationOnDemandSingletonPattern instance hashCode=" + SingletonHolder.instance.hashCode());
	}
}
