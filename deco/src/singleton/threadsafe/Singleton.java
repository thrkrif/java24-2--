package singleton.threadsafe;

public class Singleton {
    // synchronized 키워드 사용하는 방법
    // 단 메서드에 사용하여 여러 스레드가 접근할 때 성능이 저하된다.
    // 해결 하기 위해 lazyinitialization 이 나왔다.
    private static Singleton instance = null;

    private Singleton(){
        System.out.println("method synchronized thread-safe");
    }

    public static synchronized Singleton getInstance(){
        if (instance == null){
            instance = new Singleton();
        }
        return instance;
    }

    public void print() {
		System.out.println("Thread-Safe using synchronized Singleton instance hashCode=" + instance.hashCode());
	}
}
