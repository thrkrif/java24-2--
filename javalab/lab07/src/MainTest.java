// 자바프로그래밍 2분반 32207522 양상훈

import java.util.Map;

public class MainTest {
    public static void main(String[] args) {
        FileImporter<ProgramLauncherCommand> jsonImporter = new ProgramLauncherCommandJSONFileImporter();
        Map<String, ProgramLauncherCommand> jsonMap = jsonImporter.importFile("commands.json");

        FileImporter<ProgramLauncherCommand> xmlImporter = new ProgramLauncherCommandXMLFileImporter();
        Map<String, ProgramLauncherCommand> xmlMap = xmlImporter.importFile("commands.xml");

        FileLoader<ProgramLauncherCommand> csvLoader = new ProgramLauncherCommandCSVFileLoader();
        Map<String, ProgramLauncherCommand> csvMap = csvLoader.load("commands.csv");

        // === JSON 파일 처리 ===
        System.out.println("\n=== JSON File Processing ===");
        for (Map.Entry<String, ProgramLauncherCommand> entry : jsonMap.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue().getExecutable() + " " + entry.getValue().getIcon());
        }
        jsonImporter.exportFile("commands2.json", jsonMap);

        // === XML 파일 처리 ===
        System.out.println("\n=== XML File Processing ===");
        for (Map.Entry<String, ProgramLauncherCommand> entry : xmlMap.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue().getExecutable() + " " + entry.getValue().getIcon());
        }
        xmlImporter.exportFile("commands2.xml", xmlMap);

        // === CSV 파일 처리 ===
        System.out.println("\n=== CSV File Processing ===");
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
        jsonImporter.exportFile("commands3.json", jsonMap);

        // === Adapter를 사용하여 XML 파일 처리 ===
        System.out.println("\n=== XML File Processing Using Adapter ===");
        FileLoader<ProgramLauncherCommand> xmlLoaderAdapter = new FileImporterLoaderAdapter<>(xmlImporter);
        Map<String, ProgramLauncherCommand> xmlAdapterMap = xmlLoaderAdapter.load("commands.xml");
        for (Map.Entry<String, ProgramLauncherCommand> entry : xmlAdapterMap.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue().getExecutable() + " " + entry.getValue().getIcon());
        }
        xmlImporter.exportFile("commands3.xml", xmlMap);

        // === Adapter를 사용하여 CSV 파일 처리 ===
        System.out.println("\n=== CSV File Processing Using Adapter ===");
        FileImporter<ProgramLauncherCommand> csvImporterAdapter = new FileLoaderImporterAdapter<>(csvLoader);
        Map<String, ProgramLauncherCommand> csvAdapterMap = csvImporterAdapter.importFile("commands.csv");
        for (Map.Entry<String, ProgramLauncherCommand> entry : csvAdapterMap.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue().getExecutable() + " " + entry.getValue().getIcon());
        }
        csvLoader.save("commands3.csv", csvMap);

        // yourcode : Iterator Pattern
        System.out.println();
        System.out.println("Using Iterator Pattern:");
        ProgramLauncherCommandIterator jsonIterator = new ProgramLauncherCommandIterator(jsonMap);
        while (jsonIterator.hasNext()) {
            ProgramLauncherCommand command = jsonIterator.next();
            System.out.println(command.getName() + " " + command.getExecutable() + " " + command.getIcon());
        }
        jsonImporter.exportFile("commands4.json", jsonMap);
        System.out.println();

        System.out.println("Using Iterator Pattern:");
        ProgramLauncherCommandIterator xmlIterator = new ProgramLauncherCommandIterator(xmlMap);
        while (xmlIterator.hasNext()) {
            ProgramLauncherCommand command = xmlIterator.next();
            System.out.println(command.getName() + " " + command.getExecutable() + " " + command.getIcon());
        }
        xmlImporter.exportFile("commands4.xml", xmlMap);

        System.out.println();
        System.out.println("Using Iterator Pattern:");
        ProgramLauncherCommandIterator csvIterator = new ProgramLauncherCommandIterator(csvMap);
        while (csvIterator.hasNext()) {
            ProgramLauncherCommand command = csvIterator.next();
            System.out.println(command.getName() + " " + command.getExecutable() + " " + command.getIcon());
        }
        csvLoader.save("commands4.csv", csvMap);
    }
}
