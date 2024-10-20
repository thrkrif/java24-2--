package singleton.lazyinitialization;

public class Singleton {


    private static Singleton instance = null;

    private Singleton(){
        System.out.println("lazy initialization");
    }
    
    private static Singleton getInstance(){
        if (instance == null){
            synchronized(Singleton.class){
                if (instance == null){
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }
}
