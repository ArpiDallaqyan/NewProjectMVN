package FunctionalInterfaces;

@FunctionalInterface
public interface SalaryOperation {
    int calculate(Employee employee, int value);
}