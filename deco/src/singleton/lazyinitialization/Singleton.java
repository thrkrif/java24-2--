package singleton.lazyinitialization;

public class Singleton {


    private static Singleton instance = null;

    private Singleton(){
        System.out.println("lazy initialization");
    }

    public static Singleton getInstance(){
       if (instance == null){
        synchronized (Singleton.class){
            if (instance == null){
                instance = new Singleton();
            }
        }
       }
        return instance;
    }

    public void print() {
		System.out.println("Thread-Safe Singleton (Lazy Initialization with Double Checked Locking) instance hashCode=" + instance.hashCode());
	}
}
