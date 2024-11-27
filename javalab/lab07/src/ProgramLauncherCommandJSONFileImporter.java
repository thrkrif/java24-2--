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
    
    // Gson 객체 생성, Pretty Printing이 목적
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    // JSON 파일에서 데이터를 읽어서 Map<String, ProgramLauncherCommand> 형태로 반환하는 메서드
    @Override
    public Map<String, ProgramLauncherCommand> importFile(String filepath) {
        // FileReader를 사용해 JSON 파일을 읽는다.
        try (FileReader reader = new FileReader(filepath)) {
            // GSON을 이용해 JsonObject로 변환한다.
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

    // Map<String, ProgramLauncherCommand> 데이터를 JSON 파일로 저장하는 메서드
    @Override
    public void exportFile(String filepath, Map<String, ProgramLauncherCommand> map) {
        // FileWriter를 이용하여 JSON파일을 쓸 수 있도록 한다.
        try (FileWriter writer = new FileWriter(filepath)) {
            // Map 객체를 JSON 배열로 변환한다. "commands" 키 아래 저장한다.
            JsonObject root = new JsonObject();
            root.add("commands", gson.toJsonTree(map.values()));

            // Pretty Print형식으로 JSON 파일을 저장한다.
            gson.toJson(root, writer);
        } catch (IOException e) {
            throw new RuntimeException("Failed to export JSON file", e);
        }
    }
}
