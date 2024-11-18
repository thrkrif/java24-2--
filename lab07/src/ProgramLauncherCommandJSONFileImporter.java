// 자바프로그래밍 2분반 32207522 양상훈

// Gson 라이브러리를 이용하여 JSON 파일을 읽음 : Pretty Print
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProgramLauncherCommandJSONFileImporter implements FileImporter<ProgramLauncherCommand> {
    // Pretty Printing 활성화 -> 여러줄로 출력하기 위함.
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @Override
    public Map<String, ProgramLauncherCommand> importFile(String filepath) {
        try (FileReader reader = new FileReader(filepath)) {
            // JSON 파일의 루트 객체 파싱
            JsonObject root = gson.fromJson(reader, JsonObject.class);

            // "commands" 배열 읽기
            Type listType = new TypeToken<List<ProgramLauncherCommand>>() {}.getType();
            List<ProgramLauncherCommand> commands = gson.fromJson(root.get("commands"), listType);

            // 배열을 Map으로 변환
            Map<String, ProgramLauncherCommand> map = new HashMap<>();
            for (ProgramLauncherCommand command : commands) {
                map.put(command.getName(), command);
            }
            return map;
        } catch (IOException e) {
            throw new RuntimeException("Failed to import JSON file", e);
        }
    }

    @Override
    public void exportFile(String filepath, Map<String, ProgramLauncherCommand> map) {
        try (FileWriter writer = new FileWriter(filepath)) {
            // Map을 "commands" 키 아래 배열로 변환
            JsonObject root = new JsonObject();
            root.add("commands", gson.toJsonTree(map.values()));

            // Pretty Printed JSON 파일 생성
            gson.toJson(root, writer);
        } catch (IOException e) {
            throw new RuntimeException("Failed to export JSON file", e);
        }
    }
}
