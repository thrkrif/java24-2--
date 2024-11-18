// 자바프로그래밍 2분반 32207522 양상훈

import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Element; // Element 이용
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Map;

public class ProgramLauncherCommandXMLFileImporter implements FileImporter<ProgramLauncherCommand> {
    @Override
    public Map<String, ProgramLauncherCommand> importFile(String filepath) {
        Map<String, ProgramLauncherCommand> map = new HashMap<>();
        try {
            var doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(filepath);
            var commands = doc.getDocumentElement().getElementsByTagName("command");

            for (int i = 0; i < commands.getLength(); i++) {
                var element = (Element) commands.item(i);
                String name = element.getElementsByTagName("name").item(0).getTextContent();
                String executable = element.getElementsByTagName("executable").item(0).getTextContent();
                String icon = element.getElementsByTagName("icon").item(0).getTextContent();
                map.put(name, new ProgramLauncherCommand(name, executable, icon));
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to import XML file", e);
        }
        return map;
    }

    @Override
    public void exportFile(String filepath, Map<String, ProgramLauncherCommand> map) {
        try (FileWriter writer = new FileWriter(filepath)) {
            writer.write("<commands>\n");
            for (Map.Entry<String, ProgramLauncherCommand> entry : map.entrySet()) {
                ProgramLauncherCommand command = entry.getValue();
                writer.write("  <command>\n");
                writer.write("    <name>" + command.getName() + "</name>\n");
                writer.write("    <executable>" + command.getExecutable() + "</executable>\n");
                writer.write("    <icon>" + command.getIcon() + "</icon>\n");
                writer.write("  </command>\n");
            }
            writer.write("</commands>");
        } catch (Exception e) {
            throw new RuntimeException("Failed to export XML file", e);
        }
    }
}
