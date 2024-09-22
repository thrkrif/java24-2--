package template;

public class DiskScheduler {
    private SeekStrategy strategy;

    // 기본 생성자, strategy의 default = null

    public void setSeekStrategy(SeekStrategy strategy){
        this.strategy = strategy;
    }

    public void executeSeek(int[] queue, int start){
        System.out.println(strategy.getName() + " 알고리즘으로 처리");
        strategy.seek(queue, start);
    }
}
