package hibernate.services;

import hibernate.models.Client;
import org.apache.log4j.chainsaw.Main;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.Semaphore;

public class ServeAccounts implements Runnable {

    private static final Logger LOG = LoggerFactory.getLogger(Main.class);
    private final int INTERVAL;
    private final String operationType; //payment , profit
    private final int PAYMENT = 10;
    private List<Client> clients;
    Semaphore semaphore;
    ClientService clientService ;


    public ServeAccounts(Semaphore aSemaphore, int interval, String operationType) {
        this.semaphore = aSemaphore;
        INTERVAL = interval;
        this.operationType = operationType;
    }

    @Override
    public void run() {
        while (true) {
            try {
                getClients();
                Thread.sleep(INTERVAL);
                semaphore.acquire();
                clients.forEach(client -> {
                    if (this.operationType.equals("payment")) {
                        client.setAccount(client.getAccount() + PAYMENT);
                        clientService.update(client);
                    } else if (operationType.equals("profit")) {
                        int dividend = (int) (client.getAccount() / 100);
                        client.setAccount(client.getAccount() + dividend);
                        clientService.update(client);
                    }
                });
                semaphore.release();
            } catch (Exception e) {
                LOG.error(String.valueOf(e));
                return;
            }
        }
    }


    public void getClients() {
        clientService = new ClientService();
        clients = clientService.getAllClients();
    }
}
