package arithmatic;
// Reciever 객체를 참조함
public class OperationCommand implements Command{
    Operation operation;
    char operator;
    int operand;

    // Cmd class의 constructor는 Receiver 객체를 인수로 받는다.
    public OperationCommand(Operation operation, char operator, int operand){
        this.operation = operation;
        this.operator = operator;
        this.operand = operand;
    }  


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


    @Override
    public String toString() {
        return "{" +
            " operation='" + getOperation() + "'" +
            ", operator='" + getOperator() + "'" +
            ", operand='" + getOperand() + "'" +
            "}";
    }  

    // execute()에서는 Receiver의 행동 메서드를 호출한다.
    @Override
    public void execute(){
        operation.operate(operator, operand);
    }

    // 계산 반대 기능
    @Override
    public void undo(){
        try {
            operation.operate(oppsite(operator), operand);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public char oppsite(char operator) throws Exception{
        switch (operator) {
            case '+' : return '-';
            case '-' : return '+';
            case '/' : return '*';
            case '*' : return '/';
            default: throw new Exception("No Operator Exception");
         }
    }


}
