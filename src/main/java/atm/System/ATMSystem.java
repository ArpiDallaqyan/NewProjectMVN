package atm.System;

public class ATMSystem {

    public static boolean testValidAmount(int amount) {
        if (amount > 100 & amount < 20000 & amount % 100 == 0) {
            System.out.println("Valid Amount");
            return true;
        }
        return false;
    }

    public static boolean tooLowAmount(int amount) {
        if (amount < 100) {
            System.out.println("Too Low Amount");
            return true;
        }
        return false;
    }

    public static boolean tooHighAmount(int amount) {
        if (amount > 20000) {
            System.out.println("Too High Amount");
            return true;
        }
        return false;
    }

    public static boolean boundaryLowValue(int amount){
        if(amount < 100){
            System.out.println("Too Low Amount");
            return true;
        }
        return false;
    }

    public static boolean minimumValidAmount(int amount){
        if(amount == 100){
            System.out.println("Minimal Valid Amount");
            return true;
        }
        return false;
    }

    public static boolean maximalValidAmount(int amount){
        if (amount == 20000){
            System.out.println("Maximal Valid Amount");
            return true;
        }
        return false;
    }


}
