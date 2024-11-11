import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

public class ProgramLauncherCommandApp extends JFrame {
    private ProgramLauncherCommandInvoker launcher;

    public ProgramLauncherCommandApp() {
        setTitle("Program Launcher with Icons");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(0, 1));

        // 명령을 불러오는 로직
        Map<String, ProgramLauncherCommand> commandsMap = ProgramLauncherCommandsImporter.loadCommandsFromJson("commands.json");
        launcher = new ProgramLauncherCommandInvoker(commandsMap);

        // 기존 명령에 대해 버튼을 5개까지 동적으로 생성
        int count = 0;
        for (Map.Entry<String, ProgramLauncherCommand> entry : commandsMap.entrySet()) {
            if (count >= 5) break; // 5개 버튼만 생성
            JButton button = createButtonWithIcon(entry.getKey(), entry.getValue());
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    launcher.setCommand(entry.getValue());
                    launcher.executeCommand();
                }
            });
            add(button);
            count++;
        }

        // 실행 취소(Undo) 버튼을 추가
        JButton undoButton = new JButton("Undo Last Command");
        undoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                launcher.undoLastCommand();
            }
        });
        add(undoButton);

        // yourcode : 이전 상태 복구 버튼 추가
        JButton restoreButton = new JButton("Redo Command");
        restoreButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                launcher.redoCommand();
            }
        });
        add(restoreButton);
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
