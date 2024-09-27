package template;

import java.util.ArrayList;
import java.util.Arrays;

public class CSCANSeekStrategy implements SeekStrategy{
    private String name = "CSCAN";
    // 한쪽 방향으로 이동, 나머지는 SCAN과 동일
    // start로 부터 왼쪽으로 가기로 했다.
    // 1. start가 queue에 있는 경우 : head = start
    // 2. start가 queue에 없는 경우 : head는 start와 가장 가까우면서, start보다 작은 값(왼쪽 값)
    // head -> queue의 가장 작은 값까지 이동, 그 이후 queue에서 가장 큰 값 -> start 다음으로 큰 값까지 head가 이동한다.
    // 방문 로그를 만들지 않아도 상관 없지만, 나중에 사용자가 확인해 보고 싶을 수도 있으니 ArrayList를 지우지 않겠다.
    @Override
    public void seek(int[] queue, int start){
        // 오름 차순으로 정렬
        Arrays.sort(queue);

        // 방문한 헤드를 저장하는 리스트 -> 필요 없어짐.
        ArrayList<Integer> visited = new ArrayList<>();

        // 헤드, 아래의 조건에 의해 head가 설정된다.
        int head;

        // start 값이 queue에 있는지 확인
        int headIndex = Arrays.binarySearch(queue, start);
        if (headIndex >= 0) {
            // start가 queue에 있을 때
            head = queue[headIndex];
        } else {
            // start가 queue에 없을 때 start보다 작은 값 중 가장 큰 값이 head
            // headIndex = - (index + 1) 이므로, start보다 작으면서 그 중 가장 큰 값의 인덱스는 -(headIndex + 1) 이다.
            // headIndex = -(headIndex+1);
            // head = queue[headIndex];
            int insertionPoint = -(headIndex + 1);
            if (insertionPoint == 0) {
                // start보다 작은 값이 없으면, 가장 첫 번째 값이 head
                head = queue[0];
                headIndex = 0;
            } else {
                // start보다 작은 값 중 가장 큰 값이 head
                head = queue[insertionPoint - 1];
                headIndex = insertionPoint - 1;
            }
        }
        
        // start 지점의 정보를 저장한다.
        int startHead = head;
        int startHeadIndex = headIndex;

        for(int i = startHeadIndex; i >= 0; i--){
            // head가 이동한다.
            head = queue[i];
            headIndex = i;
            visited.add(head);
            System.out.printf("%d ", head);
        }
        for(int i = queue.length - 1; i > startHeadIndex; i--){
            // head가 이동한다.
            head = queue[i];
            headIndex = i;
            visited.add(head);
            System.out.printf("%d ", head);
        }

        System.out.println();
        System.out.println("방문 로그 확인: " + visited);
        System.out.println();
    }
    

    

    @Override
    public String getName(){
        return name;
    }
}
