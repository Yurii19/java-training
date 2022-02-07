import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Scanner;

public class Main {

    private static final Logger LOG = LoggerFactory.getLogger(Main.class);

    public static void main(String args[]) {
        LOG.error("HELLO");


        LinkedHashMap<Integer, Integer> billsBox = new LinkedHashMap<Integer, Integer>() {{
            put(1, 0);
            put(2, 0);
            put(5, 0);
            put(10, 10);
            put(20, 0);
            put(100, 3);
        }};

        ArrayList<Account> clients = new ArrayList<Account>() {{
            add(new Account("Yurii", 100));
            add(new Account("Evhen", 2000));
            add(new Account("Helen", 2000));
        }};

        ATM atm1 = new ATM(billsBox, clients);
        atm1.startDeposits();
        atm1.getTheInterface().askUser();
        atm1.startDeposits();
    }
}