package FunctionalInterfaces;

@FunctionalInterface
public interface Condition<T> {
    boolean validate(T t);
}