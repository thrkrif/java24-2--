package template;

public class FCSSSeekStrategy implements SeekStrategy {
    private String name = "FCSS";
    @Override
    public void seek(int[] queue, int start){
        for (int q: queue) {
            System.out.printf("%d ", q);
            }
            System.out.printf("\n");
            System.out.println();
    }

    @Override
    public String getName(){
        return name;
    }

}
