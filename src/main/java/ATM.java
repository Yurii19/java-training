import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.concurrent.Semaphore;

public class ATM {

    private final Store theStore;
    private final Semaphore theSemaphore = new Semaphore(20);
    private final UserInterface theInterface;
    private List<Account> clients = Collections.synchronizedList(new ArrayList<>());
    private Account currentAccount;

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
        } else {
            System.err.println("There is no such account");
        }
    }

    /**
     * @param accountId - field of Account class instance that used for identify it
     * @return instance of Account class that match with id argument
     */
    public Account getAccountById(String accountId) {
        Account account = this.getClients()
                .stream()
                .filter(el -> el.getOwnerId().equals(accountId))
                .findAny()
                .orElse(null);
        return account;
    }


    /**
     * Function create instance of class Account and put it into the set of accounts
     *
     * @param accountId - field of Account class instance that used for identify it
     */
    public synchronized void createAccount(String accountId) {
        this.clients.add(new Account(accountId, 1000));
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

    public synchronized List<Account> getClients() {
        return clients;
    }

    public synchronized void setClients(List<Account> clients) {
        this.clients = clients;
    }
}