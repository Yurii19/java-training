import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Scanner;

public class Main {
    public static void main(String args[]) {

        LinkedHashMap<Integer, Integer> billsBox = new LinkedHashMap<Integer, Integer>() {{
            put(1, 0);
            put(2, 0);
            put(5, 0);
            put(10, 10);
            put(20, 0);
            put(100, 3);
        }};

        Account acc1 = new Account("Yurii", 1000);
        Account acc2 = new Account("Evhen", 2000);
        ArrayList<Account> clients = new ArrayList<Account>() {{
            add(new Account("Yurii", 1000));
            add(new Account("Evhen", 2000));
            add(new Account("Helen", 2000));
        }};


        ATM atm1 = new ATM(billsBox, clients);
        atm1.theInterface.askUser();
    }
}

