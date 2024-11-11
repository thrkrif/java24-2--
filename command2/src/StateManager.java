import java.io.*;
import java.util.Stack;

class StateManager {
    private static final String STATE_FILE = "commandState.ser";

    public static void saveState(Stack<IProgramLauncherCommand> commandStack) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(STATE_FILE))) {
            out.writeObject(commandStack); // 스택을 그대로 저장
            System.out.println("Command state saved.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public static Stack<IProgramLauncherCommand> loadState() {
        Stack<IProgramLauncherCommand> commandStack = new Stack<>();
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(STATE_FILE))) {
            commandStack = (Stack<IProgramLauncherCommand>) in.readObject(); // 명령을 스택으로 불러오기
            System.out.println("Command state loaded.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("No previous state found, starting fresh.");
        }
        return commandStack;
    }
}
