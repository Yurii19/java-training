import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.concurrent.Semaphore;

public class ATM {

    private final Store theStore;
    private UserInterface theInterface;
    private List<Account> clients = Collections.synchronizedList(new ArrayList<>());
    private Account currentAccount;
    private final Semaphore theSemaphore = new Semaphore(20);

    public void startDeposits() {
        MultiplyDeposit multiplyDeposit = new MultiplyDeposit(this.clients, theSemaphore);
        PayService payService = new PayService(this.clients, theSemaphore);
        Thread multiply = new Thread(multiplyDeposit, "multiply");
        Thread paying = new Thread(payService, "paying");
        multiply.setDaemon(true);
        paying.setDaemon(true);
        multiply.start();
        paying.start();
    }

    /**
     * Function set the account for manipulate it data
     *
     * @param accountId - field of Account class instance that used for identify it
     */
    public void loginAccount(String accountId) {
        Account targetAccount = this.getAccountById(accountId);
        if (targetAccount != null) {
            this.currentAccount = targetAccount;
        }
    }

    /**
     * @param accountID - field of Account class instance that used for identify it
     * @return instance of Account class that match with id argument
     */
    public Account getAccountById(String accountID) {
        Account account = clients
                .stream()
                .filter(el -> el.getOWNER_ID().equals(accountID))
                .findAny()
                .orElse(null);
        return account;
    }


    /**
     * Function create instance of class Account and put it into the set of accounts
     *
     * @param accountID - field of Account class instance that used for identify it
     */
    public void createAccount(String accountID) {
        this.clients.add(new Account(accountID, 1000));
    }


    public ATM(LinkedHashMap<Integer, Integer> billsBox, ArrayList<Account> clients) {
        this.theStore = new Store(billsBox);
        this.theInterface = new UserInterface(this.theStore, this);
        this.clients = clients;
    }

    public synchronized Account getCurrentAccount() {
        return currentAccount;
    }

    public UserInterface getTheInterface() {
        return this.theInterface;
    }
}