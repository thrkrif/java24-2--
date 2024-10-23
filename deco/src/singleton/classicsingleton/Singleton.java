package singleton.classicsingleton;

public class Singleton {
    // 가장 기본적인 싱글톤
    // private로 default constructor
    // static으로 getInstnace() 메서드 생성

    // private static Singleton instance = null;

    // private Singleton(){
    //     System.out.println("create only one instance");
    // }

    // public static Singleton getInstance(){
    //     if (instance == null){
    //         instance = new Singleton();
    //     }
    //     return instance;
    // }


    private static Singleton instance = null;

    private Singleton(){
        System.out.println("create only one instance");
    }

    public static Singleton getInstance(){
        if (instance == null){
            synchronized (Singleton.class){
                if( instance == null){
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }


    public void print() {
		System.out.println("Classic Singleton instance hashCode=" + instance.hashCode());
	}
}


