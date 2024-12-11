package arithmetic;

public class MainTest {
    public MainTest() {
        int a = 100;
        int b = 50;
        int c = 10;
        int d = 2;
        System.out.println("a = " + a);
        System.out.println("b = " + b);
        System.out.println("c = " + c);
        System.out.println("d = " + d);
        int e = 0 + a;
        int f = e - b;
        int g = f * c;
        int h = g / d;
        System.out.printf("e = %d + %d = %d\n", 0, a, e);
        System.out.printf("f = %d - %d = %d\n", e, b, f);
        System.out.printf("g = %d * %d = %d\n", f, c, g);
        System.out.printf("h = %d / %d = %d\n", g, d, h);

        // invoker
        OperationInvoker invoker = new OperationInvoker();
        // invoker operate
        System.out.println("\ninvoker operate-----");
        invoker.operate('+', a);
        invoker.operate('-', b);
        invoker.operate('*', c);
        invoker.operate('/', d);
        // invoker undo 4
        invoker.undo(4);
        // invoker redo 4
        invoker.redo(4);
    }
}
