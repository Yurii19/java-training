package hibernate.services;

import hibernate.models.Atm;
import hibernate.models.Client;
import hibernate.models.Operation;
import hibernate.models.OperationType;
import hibernate.utils.BigBillsStrategy;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.Scanner;

@Slf4j
public class ClientInterface {

    private final ClientService clientService;
    private final AtmService atmService;
    private final Atm atm;

    private Client client;


    public ClientInterface(Atm atm, AtmService atmService) {
        this.clientService = new ClientService();
        this.atmService = atmService;
        this.atm = atm;
    }

    public void askUser() {
        Scanner sc = new Scanner(System.in);

        try {
            // Client aClient = null;
            loginClient(sc);
//            while (client == null) {
//                System.out.println(">> Please input your name");
//                String answer = sc.nextLine();
//                if (answer.equals("exit")) {
//                    this.client = null;
//                    break;
//                }
//                client = clientService.get(answer);
//
//                if (client == null) {
//                    System.err.println("User not found.");
//                    continue;
//                }
//                System.out.println(" >> Hello " + client.getName() + "!");
//            }

            while (client != null) {
                System.out.println(">> There are available commands : put 100, give 15, cash, stat, strategy. Type 'exit' for end the session ");
                String clientCommand = sc.nextLine();
                String[] commands = hibernate.utils.Parser.getInput(clientCommand);
                System.out.println(">> Commands : " + Arrays.toString(commands));
                if (commands[0].equals("exit")) {
                    client = null;
                    break;
                }
                if (commands[0].equals("put")) {
                    int amount = Integer.parseInt(commands[1]);
                    clientService.deposit(client, atm, amount);
                } else if (commands[0].equals("give")) {
                    int amount = Integer.parseInt(commands[1]);
                    clientService.claim(client, atm, amount);
                    BigBillsStrategy bigBillsStrategy = new BigBillsStrategy(atm);
                    bigBillsStrategy.giveMoney(amount);
                    atmService.update(atm);

                }
//               else if (inputtedData[0].equals("cash")) {
//                   serveStatDialog(sc);
//               } else if (inputtedData[0].equals("stat")) {
//                   theStore.printStat();
//               }
//               else if (inputtedData[0].equals("strategy")) {
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
            askUser();
        }
    }

    public void serveGiveCommand(int amount){
        clientService.claim(client, atm, amount);
        BigBillsStrategy bigBillsStrategy = new BigBillsStrategy(atm);
        bigBillsStrategy.giveMoney(amount);
        atmService.update(atm);
    }

    private void loginClient(Scanner sc) {
        while (client == null) {
            System.out.println(">> Please input your name");
            String answer = sc.nextLine();
            if (answer.equals("exit")) {
                this.client = null;
                break;
            }
            client = clientService.get(answer);

            if (client == null) {
                System.err.println("User not found.");
                continue;
            }
            System.out.println(" >> Hello " + client.getName() + "!");
        }
    }
}
