// 자바프로그래밍 2분반 32207522 양상훈

import java.util.Map;

public class MainTest {
    public static void main(String[] args) {
        // === JSON 파일 처리 ===
        System.out.println("\n=== JSON File Processing ===");
        FileImporter<ProgramLauncherCommand> jsonImporter = new ProgramLauncherCommandJSONFileImporter();
        Map<String, ProgramLauncherCommand> jsonMap = jsonImporter.importFile("commands.json");
        for (Map.Entry<String, ProgramLauncherCommand> entry : jsonMap.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue().getExecutable() + " " + entry.getValue().getIcon());
        }
        jsonImporter.exportFile("commands2.json", jsonMap);

        // === XML 파일 처리 ===
        System.out.println("\n=== XML File Processing ===");
        FileImporter<ProgramLauncherCommand> xmlImporter = new ProgramLauncherCommandXMLFileImporter();
        Map<String, ProgramLauncherCommand> xmlMap = xmlImporter.importFile("commands.xml");
        for (Map.Entry<String, ProgramLauncherCommand> entry : xmlMap.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue().getExecutable() + " " + entry.getValue().getIcon());
        }
        xmlImporter.exportFile("commands2.xml", xmlMap);

        // === CSV 파일 처리 ===
        System.out.println("\n=== CSV File Processing ===");
        FileLoader<ProgramLauncherCommand> csvLoader = new ProgramLauncherCommandCSVFileLoader();
        Map<String, ProgramLauncherCommand> csvMap = csvLoader.load("commands.csv");
        for (Map.Entry<String, ProgramLauncherCommand> entry : csvMap.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue().getExecutable() + " " + entry.getValue().getIcon());
        }
        csvLoader.save("commands2.csv", csvMap);

        // === Adapter를 사용하여 JSON 파일 처리 ===
        System.out.println("\n=== JSON File Processing Using Adapter ===");
        FileLoader<ProgramLauncherCommand> jsonLoaderAdapter = new FileImporterLoaderAdapter<>(jsonImporter);
        Map<String, ProgramLauncherCommand> jsonAdapterMap = jsonLoaderAdapter.load("commands.json");
        for (Map.Entry<String, ProgramLauncherCommand> entry : jsonAdapterMap.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue().getExecutable() + " " + entry.getValue().getIcon());
        }

        // === Adapter를 사용하여 XML 파일 처리 ===
        System.out.println("\n=== XML File Processing Using Adapter ===");
        FileLoader<ProgramLauncherCommand> xmlLoaderAdapter = new FileImporterLoaderAdapter<>(xmlImporter);
        Map<String, ProgramLauncherCommand> xmlAdapterMap = xmlLoaderAdapter.load("commands.xml");
        for (Map.Entry<String, ProgramLauncherCommand> entry : xmlAdapterMap.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue().getExecutable() + " " + entry.getValue().getIcon());
        }

        // === Adapter를 사용하여 CSV 파일 처리 ===
        System.out.println("\n=== CSV File Processing Using Adapter ===");
        FileImporter<ProgramLauncherCommand> csvImporterAdapter = new FileLoaderImporterAdapter<>(csvLoader);
        Map<String, ProgramLauncherCommand> csvAdapterMap = csvImporterAdapter.importFile("commands.csv");
        for (Map.Entry<String, ProgramLauncherCommand> entry : csvAdapterMap.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue().getExecutable() + " " + entry.getValue().getIcon());
        }
    }
}
