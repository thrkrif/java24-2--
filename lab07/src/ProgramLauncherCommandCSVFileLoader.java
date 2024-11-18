// 자바프로그래밍 2분반 32207522 양상훈

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Map;

public class ProgramLauncherCommandCSVFileLoader implements FileLoader<ProgramLauncherCommand> {
    @Override
    public Map<String, ProgramLauncherCommand> load(String filepath) {
        Map<String, ProgramLauncherCommand> map = new HashMap<>();
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

    @Override
    public void save(String filepath, Map<String, ProgramLauncherCommand> map) {
        try (FileWriter writer = new FileWriter(filepath)) {
            for (ProgramLauncherCommand command : map.values()) {
                writer.write(String.format("%s,%s,%s\n", command.getName(), command.getExecutable(), command.getIcon()));
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to save CSV file", e);
        }
    }
}
