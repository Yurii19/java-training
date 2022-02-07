import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.Semaphore;

public class ServeAccounts implements Runnable {

    private static final Logger LOG = LoggerFactory.getLogger(Main.class);
    private final List<Account> clients;
    private final int INTERVAL;
    private final String operationType; //payment , profit
    private final int PAYMENT = 10;
    Semaphore semaphore;


    public ServeAccounts(List<Account> clients, Semaphore aSemaphore, int interval, String operationType) {
        this.clients = clients;
        this.semaphore = aSemaphore;
        INTERVAL = interval;
        this.operationType = operationType;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(INTERVAL);
                semaphore.acquire();
                clients.forEach(account -> {
                    if (this.operationType.equals("payment")) {
                        account.takeMoney(this.PAYMENT);
                    } else if (operationType.equals("profit")) {
                        int dividend = account.getDeposit() / 100;
                        account.putMoney(dividend);
                    }
                });
                semaphore.release();
            } catch (Exception e) {
                LOG.error(String.valueOf(e));
                return;
            }
        }
    }
}
