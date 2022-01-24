import java.util.ArrayList;
import java.util.LinkedHashMap;

public class ATM {

    Store theStore;
    UserInterface theInterface;
    ArrayList<Account> clients;

    public ATM(LinkedHashMap<Integer, Integer> billsBox, ArrayList<Account> clients) {

        this.theStore = new Store(billsBox);
        this.theInterface = new UserInterface(this.theStore);
        this.clients = clients;
    }
}
