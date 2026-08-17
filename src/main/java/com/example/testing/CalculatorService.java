package com.example.testing;


public class CalculatorService {

    public static int sum(int a, int b) {
        return a + b;
    }
    public static int multiply(int a, int b){
        return a * b;
    }
    public static int division(int a, int b) {
        if (b == 0) {
            System.out.println("Cannot divide by 0");
        }
        return a / b;
    }

}
