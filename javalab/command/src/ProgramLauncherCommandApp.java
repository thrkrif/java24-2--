// 자바프로그래밍2 2분반 32207522 양상훈

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

public class ProgramLauncherCommandApp extends JFrame {
    private ProgramLauncherCommandInvoker launcher = new ProgramLauncherCommandInvoker();
    private Map<String, ProgramLauncherCommand> commandsMap;

    public ProgramLauncherCommandApp() {
        setTitle("Program Launcher with Icons");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(0, 1));

        // JSON 파일에서 명령을 로드
        commandsMap = ProgramLauncherCommandsImporter.loadCommandsFromJson("commands.json");

        // commandsMap의 각 항목에 대해 버튼 생성
        for (Map.Entry<String, ProgramLauncherCommand> entry : commandsMap.entrySet()) {
            JButton button = createButtonWithIcon(entry.getKey(), entry.getValue());

            // 버튼 클릭 시 프로그램 실행
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    launcher.setCommand(entry.getValue());  // invoker에 커맨드 설정
                    launcher.executeCommand();  // 프로그램 실행
                }
            });
            // GUI에 버튼 추가
            add(button);
        }

        // 실행 취소(Undo) 버튼을 GUI에 추가
        JButton undoButton = new JButton("Undo Last Command");
        undoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                launcher.undoLastCommand();  // Invoker에서 실행 취소
            }
        });
        add(undoButton);     
    }

    // 아이콘이 있는 버튼 생성 메서드
    private JButton createButtonWithIcon(String name, ProgramLauncherCommand command) {
        JButton button = new JButton(name);
        button.setHorizontalAlignment(SwingConstants.LEFT);

        // 아이콘 설정
        if (command.getIcon() != null) {
            ImageIcon icon = new ImageIcon(command.getIcon());
            button.setIcon(icon);
        }

        return button;
    }

    // 애플리케이션 실행 메서드
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ProgramLauncherCommandApp app = new ProgramLauncherCommandApp();
            app.setVisible(true);
        });
    }
}
