package hibernate.services;

import hibernate.models.Client;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Scanner;

@Slf4j
public class ClientInterface {

    private final ClientService clientService;

    public ClientInterface(ClientService clientService) {
        this.clientService = clientService;
    }

    public void askUser() {
        Scanner sc = new Scanner(System.in);

        try {
            List<Client> clients = null;


            while (clients == null) {
                System.out.println(">> Please input your identifier");
                String answer = sc.nextLine();
              //  clients = clientService.getAllClients();
                if (answer.equals("exit")){
                    break;
                }
                Client theClient = clientService.get(answer);

                if (theClient == null){
                    System.err.println("User not found.");
                    continue;
                }
                System.out.println(" >>>> " + clientService.get(answer));
                //  System.out.println("***************");
                //  clients = clientService.dao.getAll();
                //  System.out.println(" >>>> " + clients);

//               currentClient = clientService.get(2);
//               if (ask.trim().equals("exit")) {
//                   break;
//               }
//               serveLogin(sc, ask);
//               currentAccount = theATM.getCurrentAccount();
//           }
//           if (currentAccount == null) {
//               return;
//           }
//
//           String lastCommand = "";
//
//           while (!lastCommand.equals("exit")) {
//               System.out.println(">> There are available commands : put 100, give 15, cash, stat, strategy. Type 'exit' for end the session ");
//               serveNoMoney();
//               String userRequest = sc.nextLine();
//               String[] inputtedData = Parser.getInput(userRequest);
//               lastCommand = inputtedData[0];
//               System.out.println(">> Commands : " + Arrays.toString(inputtedData));
//               if (inputtedData[0].equals("put")) {
//                   servePutDialog(sc, Integer.parseInt(inputtedData[1]));
//               } else if (inputtedData[0].equals("cash")) {
//                   serveStatDialog(sc);
//               } else if (inputtedData[0].equals("stat")) {
//                   theStore.printStat();
//               } else if (inputtedData[0].equals("give")) {
//                   serveGiveDialog(sc, Integer.parseInt(inputtedData[1]));
//               } else if (inputtedData[0].equals("strategy")) {
//                   serveStrategyDialog(Integer.parseInt(inputtedData[1]));
//               } else if (inputtedData[0].equals("exit")) {
//                   return;
//               } else {
//                   System.out.println("Wrong command !");
//               }
            }
            //  System.out.println(" >>>> " + clients);
        } catch (Exception e) {
            log.error(e + ": You might have inputted wrong value.");
            // askUser();
        }
    }
}
