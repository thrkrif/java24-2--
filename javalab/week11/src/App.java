import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class App {
    public static void main(String[] args) throws Exception {
        new ducks.DuckTestDrive();
        new iterenum.IteratorEnumerationMainTest();
        new personDynamicArray.MainTest();

        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");

        for (int i = 0; i < list.size(); i++) {
            list.remove(i); // when element is deleted, the list size 
                              //decreases and the indexes of other elements change
        }
        list.forEach(System.out::println);

        /*for (String s : list) {
            list.remove(s); // ConcurrentModificationException
        }*/
        
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            String s = it.next(); // next() must be called before remove()
            it.remove();
        }
        list.forEach(System.out::println);
    }
}
