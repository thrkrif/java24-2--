package singleton.initializaion;

public class Singleton {
    // thread-safe 만족하도록
    // 인스턴스를 생성하자마자 객체를 생성한다.

    private static Singleton instance = new Singleton();

    private Singleton(){
        System.out.println("initialization instance thread-safe");
    }

    public static Singleton getInstance(){
        return instance;
    }

    public void print() {
		System.out.println("Thread-Safe Singleton (Eager Initialization) instance hashCode=" + instance.hashCode());
	}
}
