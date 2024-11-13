// 자바프로그래밍2 2분반 32207522 양상훈

import java.io.*;
import java.util.Stack;

// yourcode
// 프로세스 실행 중에 예기치 못하게 프로그램이 종료된 경우, 프로그램을 다시 실행하여
// 이전 프로세스들을 재실행 할 수 있도록 한다.
public class StateManager {

    // 상태를 저장할 파일명
    private static final String STATE_FILE = "commandState.ser";

    // commandStack의 상태를 STATE_FILE에 저장시키는 메서드
    public static void saveState(Stack<IProgramLauncherCommand> commandStack) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(STATE_FILE))) {
            out.writeObject(commandStack); // STATE_FILE에 스택을 그대로 저장
            System.out.println("Command state saved.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 파일에 저장된 스택의 상태를 로드하는 메서드
    @SuppressWarnings("unchecked")
    public static Stack<IProgramLauncherCommand> loadState() {
        Stack<IProgramLauncherCommand> commandStack = new Stack<>();
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(STATE_FILE))) {
            // readObject() : 직렬화 된 객체를 읽어들임, 파일에 저장된 명령 스택을 복원하는 부분이다.
            commandStack = (Stack<IProgramLauncherCommand>) in.readObject();
            System.out.println("Command state loaded.");
        } catch (IOException | ClassNotFoundException e) {
            // 파일이 없거나 오류가 발생한 경우, 스택이 비어있는 경우
            System.out.println("No previous state found, starting fresh."); 
        }
        return commandStack;
    }
}
