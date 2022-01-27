import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class ATM {

    Store theStore;
    UserInterface theInterface;
    List<Account> clients;
    Account currentAccount = null;

    public Account getCurrentAccount() {
        return currentAccount;
    }

    /**
     * Function set the account for manipulate it data
     * @param accountId - field of Account class instance that used for identify it
     */
    public void loginAccount(String accountId){
        Account targetAccount = this.getAccountById(accountId);
        if (targetAccount != null){
            this.currentAccount = targetAccount;
        }
    }

    /**
     * @param accountID - field of Account class instance that used for identify it
     * @return instance of Account class that match with id argument
     */
    public Account getAccountById(String accountID) {
        Account account = clients.stream().filter(el -> el.getOWNER_ID().equals(accountID)).findAny().orElse(null);
        return account;
    }


    /**
     * Function create instance of class Account and put it into the set of accounts
     * @param accountID - field of Account class instance that used for identify it
     */
    public void createAccount(String accountID){
        this.clients.add(new Account(accountID, 1000));
    }


    public ATM(LinkedHashMap<Integer, Integer> billsBox, ArrayList<Account> clients) {
        this.theStore = new Store(billsBox);
        this.theInterface = new UserInterface(this.theStore, this);
        this.clients = clients;
    }
}
