package arithmetic;

import java.util.ArrayList;
import java.util.List;

// Invoker
public class OperationInvoker {
    Operation operation = new Operation();
    List<Command> commands = new ArrayList<>(); // undo/redo list
    int current = 0; // current index

    // operate
    public void operate(char operator, int operand) {
        // create command and execute it
        Command command = new OperationCommand(operation, operator, operand);
        command.execute();
        // add command in the undo/redo list
        commands.add(command);
        // increase index
        current++;
    }
    // undo
    public void undo(int levels) {
        System.out.printf("\n ---- undo %d levels\n", levels);
        // perform undo
        for (int i = 0; i < levels; i++) {
            if (current > 0) {
                Command command = commands.get(--current); // decrease current
                command.undo();    
            }
        }
    }
    // redo
    public void redo(int levels) {
        System.out.printf("\n ---- redo %d levels\n", levels);
        // perform redo
        for (int i = 0; i < levels; i++) {
            if (current < commands.size()) {
                Command command = commands.get(current++); // increase current
                command.execute();    
            }
        }
    }
}
