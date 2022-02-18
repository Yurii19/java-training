package hibernate.services;

import hibernate.models.*;
import hibernate.utils.BigBillsStrategy;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Array;
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
                    serveGiveCommand(amount);
//                    clientService.claim(client, atm, amount);
//                    BigBillsStrategy bigBillsStrategy = new BigBillsStrategy(atm);
//                    bigBillsStrategy.giveMoney(amount);
//                    atmService.update(atm);
                } else if (commands[0].equals("cash")) {
                    serveCashDialog();
                }
               else if (commands[0].equals("stat")) {
                   //commands.printStat();
                    serveStatCommands();
               }
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

    public void serveStatCommands() {
        System.out.println(" << serveStatCommands >> ");
        StatisticService statisticService = new StatisticService(Statistic.class);
        Statistic statistic = (Statistic) statisticService.get(1);
        statistic.updateSlots(null, OperationType.PUT);
        System.out.println("Statistic ->>> " + statistic);
    }

    public void serveCashDialog() {
        int[] keys = new int[]{1, 2, 5, 10, 20, 50, 100};
        for (int key : keys){
            System.out.println("Nominal : "+ key+", number : "+ atm.getAmountBills(key));
        }
        // atm.getAmountBills()
    }

    public void serveGiveCommand(int amount) {
        clientService.claim(client, atm, amount);
        BigBillsStrategy bigBillsStrategy = new BigBillsStrategy(atm);
        bigBillsStrategy.giveMoney(amount);
        atmService.update(atm);
        //
        StatisticService statisticService = new StatisticService(Statistic.class);

       // Statistic statistic = statisticService.get()
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
