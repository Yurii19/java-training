import hibernate.models.Client;
import hibernate.services.ClientInterface;
import hibernate.services.ClientService;

public class Main {

    public static void main(String args[]) {
       ClientService clientService = new ClientService();

      // Client h = clientService.get("Helen");
      //  System.out.println("HHHHHHHHHHHHHHH-> "+ h);
//        Client client = clientService.createNew(new Client("Ross"));
//        System.out.println(client.getName() + " - - - - " + client.getId());
//        String name = clientService.get(3).getName();
//        System.out.println(name);
//
//        Operation op = new Operation();
      //  clientService.getAllClients();

        ClientInterface clientInterface = new ClientInterface(clientService);

        clientInterface.askUser();

//        LinkedHashMap<Integer, Integer> billsBox = new LinkedHashMap<Integer, Integer>() {{
//            put(1, 0);
//            put(2, 0);
//            put(5, 0);
//            put(10, 10);
//            put(20, 0);
//            put(100, 3);
//        }};
//
//        ArrayList<Account> clients = new ArrayList<Account>() {{
//            add(new Account("Yurii", 100));
//            add(new Account("Evhen", 2000));
//            add(new Account("Helen", 2000));
//        }};
//
//        ATM atm1 = new ATM(billsBox, clients);
//        atm1.startDeposits();
//        atm1.getTheInterface().askUser();
//        atm1.startDeposits();
    }
}