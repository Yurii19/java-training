import java.util.LinkedHashMap;

public class ATM {

    Store theStore;
    UserInterface theInterface;
    Account[] owners;

    public ATM(LinkedHashMap<Integer, Integer> billsBox) {

        this.theStore = new Store(billsBox);
        this.theInterface = new UserInterface(this.theStore);
    }

//    public ATM() {
//
//    }
}
