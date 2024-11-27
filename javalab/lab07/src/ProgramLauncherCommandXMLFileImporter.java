// 자바프로그래밍 2분반 32207522 양상훈

import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Element; // Element 이용
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Map;

public class ProgramLauncherCommandXMLFileImporter implements FileImporter<ProgramLauncherCommand> {
    
    // XML 파일을 읽어와 Map으로 변환
    @Override
    public Map<String, ProgramLauncherCommand> importFile(String filepath) {
        Map<String, ProgramLauncherCommand> map = new HashMap<>();
        try {
            var doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(filepath); // XML 파일 파싱
            var commands = doc.getDocumentElement().getElementsByTagName("command"); // XML 파일 내 <command> 태그들 가져오기 

            // 각 <command> 요소를 순회하며 데이터를 추출
            for (int i = 0; i < commands.getLength(); i++) {
                var element = (Element) commands.item(i);
                String name = element.getElementsByTagName("name").item(0).getTextContent();
                String executable = element.getElementsByTagName("executable").item(0).getTextContent();
                String icon = element.getElementsByTagName("icon").item(0).getTextContent();
                // 추출한 데이터를 Map에 저장
                map.put(name, new ProgramLauncherCommand(name, executable, icon));
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to import XML file", e);
        }
        return map;
    }

    // Map 데이터를 XML 형식으로 저장
    @Override
    public void exportFile(String filepath, Map<String, ProgramLauncherCommand> map) {
        try (FileWriter writer = new FileWriter(filepath)) {
            writer.write("<commands>\n");
            // Map의 값 가져오기
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
