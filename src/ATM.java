import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class ATM {

    Store theStore;
    UserInterface theInterface;
    List<Account> clients;

    public boolean checkIsUser(Account theAccount){
       return this.clients.contains(theAccount);
       //boolean res = clients.stream().findFirst(theAccount);
    }

    public void  addAccount(Account newUser){
        this.clients.add(newUser);
    }

    public ATM(LinkedHashMap<Integer, Integer> billsBox, ArrayList<Account> clients) {
        this.theStore = new Store(billsBox);
        this.theInterface = new UserInterface(this.theStore);
        this.clients = clients;
    }
}
