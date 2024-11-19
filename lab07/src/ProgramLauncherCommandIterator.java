// 자바프로그래밍 2분반 32207522 양상훈
import java.util.Iterator;
import java.util.Map;


// yourcode
// Iterator 패턴을 사용하여 Map의 ProgramLauncherCommand 요소를 순회
public class ProgramLauncherCommandIterator implements Iterator<ProgramLauncherCommand> {
    
    // Map의 값들을 순회하기 위한 Iterator
    private final Iterator<ProgramLauncherCommand> iterator; 

    // Map을 받아서 해당 Map의 값들을 순회할 Iterator를 생성
    public ProgramLauncherCommandIterator(Map<String, ProgramLauncherCommand> map) {
        this.iterator = map.values().iterator(); // Map의 value를 기반으로 Iterator 생성
    }

    // 순회할 다음 요소가 있는지 여부를 반환
    @Override
    public boolean hasNext() {
        return iterator.hasNext();
    }

    // 현재 Iterator가 가리키는 다음 요소를 반환
    @Override
    public ProgramLauncherCommand next() {
        return iterator.next();
    }

}
