import java.util.List;
import java.util.concurrent.Semaphore;

public class PayService implements Runnable {
    private final List<Account> clients;
    Semaphore semaphore;

    public PayService(List<Account> clients, Semaphore aSemaphore) {
        this.clients = clients;
        this.semaphore = aSemaphore;
    }

    @Override
    public void run() {
        while (true) {
            try {

                Thread.sleep(60000);
                semaphore.acquire();
                clients.forEach(account -> {
                    if (account.getDeposit() >= 10) {
                        account.takeMoney(10);
                    }
                });
                semaphore.release();
            } catch (Exception e) {
                e.printStackTrace();
                semaphore.release();
                return;
            }
        }
    }
}
