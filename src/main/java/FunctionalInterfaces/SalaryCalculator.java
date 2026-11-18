package FunctionalInterfaces;

public class SalaryCalculator {

    public static final SalaryOperation ADD_BONUS = (employee1, bonus) -> employee1.getSalary() + bonus;

    public static final SalaryOperation RAISE_PERCENT = (e, percent) ->
            e.getSalary() + e.getSalary() * percent / 100;

    public static int operate(Employee employee, int value, SalaryOperation operation) {
        if (employee == null || operation == null) {
            throw new IllegalArgumentException("Employee and operation must not be null");
        }
        if (value < 0) {
            throw new IllegalArgumentException("Value must not be negative");
        }
        return operation.calculate(employee, value);
    }
}