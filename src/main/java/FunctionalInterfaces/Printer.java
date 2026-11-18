package FunctionalInterfaces;

import java.util.List;

public class Printer {

    public static <T> void printMatched(List<T> list, Condition<T> condition) {
        for (T item : list) {
            if (condition.validate(item)) {
                System.out.println(item);
                return;
            }
        }
        System.out.println("No match found");
    }
}