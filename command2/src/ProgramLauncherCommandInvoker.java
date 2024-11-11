import java.util.Map;
import java.util.Stack;

public class ProgramLauncherCommandInvoker {
    private IProgramLauncherCommand command;
    private Stack<IProgramLauncherCommand> commandStack = new Stack<>();
    private Map<String, ProgramLauncherCommand> commandsMap;

    // 생성자에서 명령을 불러오고 상태를 로드
    public ProgramLauncherCommandInvoker(Map<String, ProgramLauncherCommand> commandsMap) {
        this.commandsMap = commandsMap;
        // yourcode : 이전 상태 복원
        commandStack = StateManager.loadState();
        if (!commandStack.isEmpty()) {
            System.out.println("Previous commands found.");
        }
    }

    public void setCommand(IProgramLauncherCommand command) {
        this.command = command;
    }

    public void executeCommand() {
        if (command != null) {
            command.execute();
            commandStack.push(command);  // 실행한 명령을 스택에 푸시
            StateManager.saveState(commandStack);  // yourcode : 상태 저장
        }
    }

    public void undoLastCommand() {
        if (!commandStack.isEmpty()) {
            IProgramLauncherCommand lastCommand = commandStack.pop();
            lastCommand.undo();
            StateManager.saveState(commandStack);  // yourcode : 상태 저장
        } else {
            System.out.println("No commands to undo.");
        }
    }

    // yourcode
    public void redoCommand() {
        if (commandStack.isEmpty()) {
            System.out.println("No previous state to restore.");
            return;
        }

        for (IProgramLauncherCommand cmd : commandStack) {
            cmd.execute();  // 실행 정보로 프로세스를 재실행
        }
    }

    public Map<String, ProgramLauncherCommand> getCommandsMap() {
        return this.commandsMap;
    }
}
