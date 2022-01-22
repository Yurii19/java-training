import java.util.LinkedHashMap;
import java.util.Scanner;

public class Main {
    public static void main(String args[]) {
        //  Test.printMap();
        //UserInterface.askUser();
        LinkedHashMap<Integer, Integer> billsBox = new LinkedHashMap<Integer, Integer>() {{
            put(1, 0);
            put(2, 0);
            put(5, 0);
            put(10, 10);
            put(20, 0);
            put(100, 3);
        }};

        ATM atm1 = new ATM(billsBox);
        atm1.theInterface.askUser();
    }
}

