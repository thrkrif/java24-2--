package arithmetic;

public class OperationCommand implements Command {
    Operation operation; // Receiver
    char operator;
    int operand;

    // constructor
    public OperationCommand(Operation operation, char operator, int operand) {
        this.operation = operation;
        this.operator = operator;
        this.operand = operand;
    }
    // getter/setter

    public Operation getOperation() {
        return this.operation;
    }

    public void setOperation(Operation operation) {
        this.operation = operation;
    }

    public char getOperator() {
        return this.operator;
    }

    public void setOperator(char operator) {
        this.operator = operator;
    }

    public int getOperand() {
        return this.operand;
    }

    public void setOperand(int operand) {
        this.operand = operand;
    }

    // toString
    @Override
    public String toString() {
        return "{" +
            " operation='" + getOperation() + "'" +
            ", operator='" + getOperator() + "'" +
            ", operand='" + getOperand() + "'" +
            "}";
    }

    @Override
    public void execute() {
        operation.operate(operator, operand);
    }

    @Override
    public void undo() {
        try {
            operation.operate(opposite(operator), operand);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
        
    private char opposite(char operator) throws Exception {
        switch(operator) {
        case '+': return '-';
        case '-': return '+';
        case '*': return '/';
        case '/': return '*';
        default: throw new Exception("No Operator Exception");
        }
    }

}
