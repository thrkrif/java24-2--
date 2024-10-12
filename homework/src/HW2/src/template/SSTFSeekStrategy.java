package HW2.src.template;
import java.util.ArrayList;
import java.util.Arrays;

public class SSTFSeekStrategy implements SeekStrategy{
    private String name = "SSTF";

    // 1. start가 queue 안에 있는 수라면 head = start
    // 2. start가 queue 안에 없다면, start에서 가장 가까운 위치를 찾아 그것이 head가 된다.
    // 한 번 방문한 헤드를 다시 방문할 수 없다. -> 방문 로그 남기기
    // 헤드에서 다음으로 갈 양쪽 거리가 같으면 왼쪽으로 가는걸로 하기.
    @Override
    public void seek(int[] queue, int start){
        // 오름 차순으로 정렬
        Arrays.sort(queue);

        // 방문한 헤드를 저장하는 리스트
        ArrayList<Integer> visited = new ArrayList<>();

        // 헤드, 아래의 조건에 의해 head가 설정된다.
        int head;

        // start 값이 queue에 있는지 확인
        int headIndex = Arrays.binarySearch(queue, start);
        if (headIndex >= 0) {
            // start가 queue에 있을 때
            head = queue[headIndex];
        } 
        else {
            // start가 queue에 없을 때 가장 가까운 값 찾기
            head = findClosest(queue, start);
        }

        // 위에서 찾은 첫번째 head를 저장
        visited.add(head);
        System.out.printf("%d ", head);
        
        // queue와 visted의 사이즈가 같으면 모든 요소를 방문한 것이므로 루프 탈출.
        while (visited.size() < queue.length) {
            // 방문하지 않은 값 중에서 head에 가장 가까운 값 찾기
            int nextHead = findNext(queue, head, visited);

            if (nextHead == -1) break;  // 더 이상 방문할 값이 없으면 종료

            // head 갱신
            head = nextHead;
            visited.add(head);

            System.out.printf("%d ", head);
        }
        System.out.println();
        System.out.println();
    }

    // queue에 start가 존재하지 않을 때, start에서 가장 가까운 값 찾기
    private int findClosest(int[] queue, int start) {
        int closest = queue[0];
        int minDistance = Math.abs(start - closest);

        for (int i = 1; i < queue.length; i++) {
            int distance = Math.abs(start - queue[i]);
            if (distance < minDistance) {
                closest = queue[i];
                minDistance = distance;
            } 
            // start로 부터 양쪽의 거리가 같으면 왼쪽 값(더 작은 값)을 선택한다.
            // else if를 만족하면 더 이상 for문을 돌 필요가 없다. 바로 for문을 빠져나오면 된다.
            // 어차피 이후 for문 실행 결과는 항상 minDistance < distance 이다.
            else if (distance == minDistance) {
                return closest;
            }
        }
        return closest;
    }

    // 방문하지 않은 값 중에서 현재 head에 가장 가까운 값 찾기
    private int findNext(int[] queue, int head, ArrayList<Integer> visited) {
        int closest = -1;
        int minDistance = Integer.MAX_VALUE;

        for (int value : queue) {
            // 방문 기록이 없는 값에만 접근
            if (!visited.contains(value)) {
                int distance = Math.abs(head - value);
                if (distance < minDistance) {
                    closest = value;
                    minDistance = distance;
                } 
                else if (distance == minDistance) {
                    return closest;
                }
            }
        }
        return closest;
    }

    @Override
    public String getName(){
        return name;
        
    }
}
