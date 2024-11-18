// 자바프로그래밍 2분반 32207522 양상훈

import java.util.Map;
// 새로운 파일 형식을 생성하고 데이터를 저장할 때 사용
public interface FileLoader<T> {
    Map<String, T> load(String filepath); // 파일 경로에서 데이터를 로드
    void save(String filepath, Map<String,T> map); // 데이터를 파일로 저장
}
