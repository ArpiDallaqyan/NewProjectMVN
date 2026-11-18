package FITest;

import FunctionalInterfaces.Employee;
import FunctionalInterfaces.Printer;
import org.junit.jupiter.api.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class PrinterTest {

    private final PrintStream originalOut = System.out;
    private ByteArrayOutputStream out;

    private final List<Employee> employees = List.of(
            new Employee("Ani", 25, 1000),
            new Employee("Karen", 40, 2500),
            new Employee("Mariam", 35, 3000)
    );

    @BeforeEach
    void setUp() {
        out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
    }

    @Test
    void printsFirstMatchOnlyTest() {
        Printer.printMatched(employees, e -> e.getSalary() > 2000);
        assertEquals("Karen (age 40, salary 2500)", out.toString().trim());
    }

    @Test
    void printsByAgeTest() {
        Printer.printMatched(employees, e -> e.getAge() < 30);
        assertEquals("Ani (age 25, salary 1000)", out.toString().trim());
    }

    @Test
    void noMatchTest() {
        Printer.printMatched(employees, e -> e.getSalary() > 10000);
        assertEquals("No match found", out.toString().trim());
    }
}