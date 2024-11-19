// 자바프로그래밍 2분반 32207522 양상훈

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Map;

public class ProgramLauncherCommandCSVFileLoader implements FileLoader<ProgramLauncherCommand> {
    
    // CSV 파일을 로드하여 ProgramLauncherCommand 객체로 변환 후 Map에 저장
    @Override
    public Map<String, ProgramLauncherCommand> load(String filepath) {
        Map<String, ProgramLauncherCommand> map = new HashMap<>();
        // BufferedReader 객체 생성 : 파일 읽기 위함
        try (BufferedReader reader = new BufferedReader(new FileReader(filepath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    map.put(parts[0], new ProgramLauncherCommand(parts[0], parts[1], parts[2]));
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to load CSV file", e);
        }
        return map;
    }

    // Map에 저장된 데이터를 CSV 파일로 저장
    @Override
    public void save(String filepath, Map<String, ProgramLauncherCommand> map) {
        // FileWriter : 파일에 데이터 쓰기
        try (FileWriter writer = new FileWriter(filepath)) {
            for (ProgramLauncherCommand command : map.values()) {
                writer.write(String.format("%s,%s,%s\n", command.getName(), command.getExecutable(), command.getIcon()));
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to save CSV file", e);
        }
    }
}
