import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;

public class ATM {

    Store theStore;
    UserInterface theInterface;
    List<Account> clients;
    Account currentAccount = null;

    public Account getCurrentAccount() {
        return currentAccount;
    }

    public void loginAccount(String accountId){
        Account targetAccount = this.getAccountById(accountId);
        if (targetAccount != null){
            this.currentAccount = targetAccount;
        }
    }

    public Account getAccountById(String accountID) {
        Account account = clients.stream().filter(el -> el.getOWNER_ID().equals(accountID)).findAny().orElse(null);
        return account;
    }



    public void createAccount(String accountID){
       // Account newAccount = new Account(accountID, 1000);
        this.clients.add(new Account(accountID, 1000));
    }


    public ATM(LinkedHashMap<Integer, Integer> billsBox, ArrayList<Account> clients) {
        this.theStore = new Store(billsBox);
        this.theInterface = new UserInterface(this.theStore, this);
        this.clients = clients;
    }
}
