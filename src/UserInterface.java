import java.util.Arrays;
import java.util.Scanner;

public class UserInterface {

    Store theStore;
    ATM theATM;

    public UserInterface(Store aStore, ATM anATM) {
        this.theStore = aStore;
        this.theATM = anATM;
    }

    /**
     * @throws if user input value that do not match any of offered command
     */
    void askUser() {
        Scanner sc = new Scanner(System.in);

        try {
            Account currentAccount = theATM.getCurrentAccount();;
            while (currentAccount == null){
                System.out.println(">> Please input your identifier");
                String ask = sc.nextLine();
                serveLogin(sc, ask);
                currentAccount = theATM.getCurrentAccount();
            }

            System.out.println(">> There are available commands : put 100, give 15, cash, stat, strategy. Type 'exit' for end the session ");
            serveNoMoney();
            String userRequest = sc.nextLine();
            String[] inputtedData = Parser.getInput(userRequest);
            System.out.println(">> Commands : " + Arrays.toString(inputtedData));
            if (inputtedData[0].equals("put")) {
                servePutDialog(sc, Integer.parseInt(inputtedData[1]));
            }
            if (inputtedData[0].equals("cash")) {
                serveStatDialog(sc);
            }
            if (inputtedData[0].equals("stat")) {
                theStore.printStat();
            }
            if (inputtedData[0].equals("give")) {
                serveGiveDialog(sc, Integer.parseInt(inputtedData[1]));
            }
            if (inputtedData[0].equals("strategy")) {
                serveStrategyDialog(Integer.parseInt(inputtedData[1]));
            }
            if (userRequest.equals("exit")) {
                return;
            } else {
                askUser();
            }

        } catch (Exception e) {
            System.err.println(e + ": You might have inputted wrong value.");
            askUser();
        }
    }

    /**
     *
     * @param sc instance of class Scaner
     * @param userId - field of Account class instance that used for identify it
     */
    private void serveLogin(Scanner sc, String userId) {
        Account targetAccount = theATM.getAccountById(userId);
        if (targetAccount != null) {
            theATM.loginAccount(userId);
            System.out.println("Hello " + userId + " !");
        } else {
            System.out.println(">> The user " + userId + " not found ! Would u like create new user  " + userId + " ? Y/N");
            String yesOrNo = sc.nextLine();
            if (yesOrNo.equals("Y")) {
                theATM.createAccount(userId);
                theATM.loginAccount(userId);
            } else if (yesOrNo.equals("N")) {
                return;
            }
        }
    }

    private void serveNoMoney() {
        if (!theStore.checkBills()) {
            System.out.println("There no money at the ATM !");
        }
    }

    /**
     * @param strategyVariant number associated with the specified strategy of giving money
     */
    private void serveStrategyDialog(int strategyVariant) {
        theStore.changeStrategy(strategyVariant);
    }

    /**
     * @param sc instance of class Scanner
     */
    private void serveGiveDialog(Scanner sc, int amountOfMoney) {
        theStore.giveMoney(amountOfMoney, theATM);
    }


    /**
     * @param sc instance of class Scanner
     */
    private void serveStatDialog(Scanner sc) {
        theStore.printCash();
    }


    /**
     * @param sc   instance of class Scanner
     * @param bill nominal of the bill
     */
    private void servePutDialog(Scanner sc, int bill) {
        if (bill == 1 || bill == 2 || bill == 5 || bill == 10 || bill == 20 || bill == 100) {
            theStore.addBill(bill, theATM);
        } else {
            System.out.println(">> Input a valid bill nominal.");
        }
    }
}
