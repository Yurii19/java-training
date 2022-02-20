package hibernate.services;

import hibernate.models.*;
import hibernate.utils.BalancedStrategy;
import hibernate.utils.BigBillsStrategy;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Scanner;

@Slf4j
public class ClientInterface {

    private final ClientService clientService;
    private final AtmService atmService;
    private final Atm atm;
    private Client client;
    private int strategy = 0;

    public ClientInterface(Atm atm, AtmService atmService) {
        this.clientService = new ClientService();
        this.atmService = atmService;
        this.atm = atm;
    }

    public void askUser() {
        Scanner sc = new Scanner(System.in);

        try {
            loginClient(sc);

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
                    servePutCommand(amount);
                } else if (commands[0].equals("give")) {
                    int amount = Integer.parseInt(commands[1]);
                    serveGiveCommand(amount);
                } else if (commands[0].equals("cash")) {
                    serveCashDialog();
                } else if (commands[0].equals("stat")) {
                    serveStatCommands();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
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
        for (int key : keys) {
            System.out.println("Nominal : " + key + ", number : " + atm.getAmountBills(key));
        }
    }

    public void servePutCommand(int amount) {
        clientService.deposit(client, atm, amount);
        atm.setAmountBills(amount, 1);
        atmService.update(atm);
        StatisticService statisticService = new StatisticService(Statistic.class);
        Statistic theStat = (Statistic) statisticService.get((int) atm.getId());
        theStat.updateSlot(amount, 1, OperationType.PUT);
        statisticService.update(theStat);
    }

    public void serveGiveCommand(int amount) {

        if (strategy == 0) {
            clientService.claim(client, atm, amount);
            BigBillsStrategy bigBillsStrategy = new BigBillsStrategy(atm);
            bigBillsStrategy.giveMoney(amount);
        }
        if (strategy == 1) {
            clientService.claim(client, atm, amount);
            BalancedStrategy balancedStrategy = new BalancedStrategy(atm);
            balancedStrategy.giveMoney(amount);
        }
        StatisticService statisticService = new StatisticService(Statistic.class);
        Statistic theStat = (Statistic) statisticService.get((int) atm.getId());
        theStat.updateSlot(amount, 1, OperationType.GET);
        statisticService.update(theStat);
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
