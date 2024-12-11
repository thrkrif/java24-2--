package arithmatic;
// Receiver : 행동을 하는 곳
// operate를 할 것임
public class Operation {
    int value = 0;
    public void operate(char operator, int operand){
        int prevValue = value;
        switch (operator) {
           case '+' : value += operand; break;
           case '-' : value -= operand; break;
           case '/' : value /= operand; break;
           case '*' : value *= operand; break;
        }
        System.out.println("operate : " + prevValue + " " + operator + " " + operand + 
        " = " + value);
    }
}
