public class Singleton {
    

    private Singleton(){
        System.out.println(" create static Singlton instance");
    }

    private static class SingletonHolder{
        private static final Singleton insance = new Singleton();
    }

    public static Singleton getInstance(){
        return SingletonHolder.insance;
    }

    public void print() {
		System.out.println("Thread-Safe Singleton (Eager Initialization) instance hashCode=" + instance.hashCode());
	}
}
