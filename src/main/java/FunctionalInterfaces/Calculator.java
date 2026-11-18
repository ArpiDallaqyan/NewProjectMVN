package FunctionalInterfaces;

public class Calculator {

    public static final NumberOperations ADD = (a, b) -> a + b;
    public static final NumberOperations SUBTRACT = (a, b) -> a - b;
    public static final NumberOperations MULTIPLY = (a, b) -> a * b;
    public static final NumberOperations DIVIDE   = (a, b) -> a / b;

    public static int operation(int a, int b, NumberOperations operation) {
        if (operation == null) {
            throw new IllegalArgumentException("Operation cannot be null");
        }
        return operation.calculate(a, b);
    }
}
