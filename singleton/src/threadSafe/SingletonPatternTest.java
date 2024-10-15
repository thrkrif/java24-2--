package threadSafe;

import java.time.Duration;
import java.time.Instant;

//https://blog.seotory.com/post/2016/03/java-singleton-pattern

public class SingletonPatternTest {
	static final int NUM_THREADS = 5;
	public SingletonPatternTest() {
		/*
		Singleton instance = Singleton.getInstance();
		instance.print();
		System.out.println(Integer.toHexString(System.identityHashCode(instance)));
		instance = Singleton.getInstance();
		instance.print();
		System.out.println(Integer.toHexString(System.identityHashCode(instance)));
		*/
		System.out.println("thread safe Singleton");
		Instant startTime = Instant.now();
		Thread[] th = new Thread[NUM_THREADS];
		for (int i = 0; i < NUM_THREADS; i++) {
			th[i] = new Thread(new Runnable() {
				@Override
				public void run() {
					Singleton instance = Singleton.getInstance();
					instance.print();
					System.out.println(Integer.toHexString(System.identityHashCode(instance)));
				}
			});
			th[i].start();
		}		
		for (Thread t : th) {
			try {
				t.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}			
		}
		
		Instant endTime = Instant.now();
		long elapsedTime = Duration.between(startTime, endTime).toMillis();
		System.out.println("thread safe Singleton elapsedTime: " + elapsedTime + " ms");	
	}
}
