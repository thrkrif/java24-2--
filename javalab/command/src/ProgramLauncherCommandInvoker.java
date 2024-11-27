// 자바프로그래밍2 2분반 32207522 양상훈
// Invoker
import java.util.Stack;

public class ProgramLauncherCommandInvoker {
    IProgramLauncherCommand command; // Command Interface 참조
    Stack<IProgramLauncherCommand> commandstack = new Stack<>(); // Command 객체들을 담을 스택을 생성

    // 커맨드 설정 메서드
    public void setCommand(IProgramLauncherCommand command) {
        this.command = command;  // 커맨드 초기화
    }

    // 커맨드를 실행하고 스택에 추가하는 메서드
    public void executeCommand() {
        if (command != null) {
            command.execute();  // process.start()
            commandstack.push(command);  // 실행한 커맨드를 스택에 추가
        }
    }

    // 마지막 커맨드를 취소하는 메서드
    public void undoLastCommand() {
        if (!commandstack.isEmpty()) {
            IProgramLauncherCommand lastCommand = commandstack.pop();  // 스택에서 마지막 명령을 꺼냄
            lastCommand.undo(); // 실행 취소
        } else {
            System.out.println("No commands to undo.");
        }
    }
}
