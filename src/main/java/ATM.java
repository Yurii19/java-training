import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;

public class ATM {

    private final Store theStore;
    private UserInterface theInterface;
    private List<Account> clients =  Collections.synchronizedList(new ArrayList<>());
    private Account currentAccount;

    public void startDeposits(){
       // System.out.println("^^^ ");
       MultiplyDeposit p1 = new MultiplyDeposit((this.clients));
    //    PayService p2 = new PayService(this.clients);
       Thread  pt = new Thread(p1,"th1");
       pt.start();
       // p2.run();
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