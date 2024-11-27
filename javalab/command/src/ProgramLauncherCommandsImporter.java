// 자바프로그래밍2 2분반 32207522 양상훈
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ProgramLauncherCommandsImporter {

    public static Map<String, ProgramLauncherCommand> loadCommandsFromJson(String jsonFilePath) {
        Map<String, ProgramLauncherCommand> commandMap = new HashMap<>();

        try (FileReader reader = new FileReader(jsonFilePath)) {
            // JSONParser를 사용하여 JSON 파일을 파싱한다.
            JSONParser parser = new JSONParser();
            JSONObject jsonObject = (JSONObject) parser.parse(reader);

            // "commands" 배열을 가져온다.
            JSONArray commandsArray = (JSONArray) jsonObject.get("commands");

            for (Object obj : commandsArray) {
                JSONObject commandData = (JSONObject) obj;
                
                String name = (String) commandData.get("name");
                String executable = (String) commandData.get("executable");
                String icon = (String) commandData.get("icon");

                // ProgramLauncherCommand 객체 생성 후 Map에 추가
                ProgramLauncherCommand command = new ProgramLauncherCommand(executable, icon);
                commandMap.put(name, command);
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

        return commandMap;
    }
}
