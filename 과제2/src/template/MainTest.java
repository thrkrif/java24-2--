package template;
// import java.util.Arrays;

public class MainTest {

    public MainTest() {
        int[] queue = { 70,  153,  24,  57,  140,  15,  115,  80, 85 };
        int start = 43;

        // int[] newQueue = Arrays.copyOf(queue, queue.length);
        // Arrays.sort(newQueue);
        // System.out.println("정렬된 큐: " + Arrays.toString(newQueue));
        

        SeekStrategy[] seekStrategies  = {new FCSSSeekStrategy(), new SSTFSeekStrategy(), 
            new SCANSeekStrategy(), new CSCANSeekStrategy()
        };
        DiskScheduler scheduler = new DiskScheduler();

        for (SeekStrategy seekStrategy : seekStrategies) {
            scheduler.setSeekStrategy(seekStrategy);
            scheduler.executeSeek(queue,start);
        }


    }
}
