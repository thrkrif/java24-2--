// 자바프로그래밍 2분반 32207522 양상훈

import java.util.Map;
// 기존 파일을 기반으로 데이터를 읽고 쓰기
public interface FileImporter<T> {
    Map<String, T> importFile(String filepath); // 파일 경로에서 데이터 가져오기
    void exportFile(String filepath, Map<String, T> map); // 데이터를 파일로 내보내기
}
