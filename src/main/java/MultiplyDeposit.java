import java.util.List;
import java.util.concurrent.Semaphore;

public class MultiplyDeposit implements Runnable {
    private final List<Account> clients;
    Semaphore semaphore;

    public MultiplyDeposit(List<Account> clients, Semaphore aSemaphore) {
        this.clients = clients;
        this.semaphore = aSemaphore;
    }


    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(10000);
                semaphore.acquire();
                System.out.println("currentThread name -> " + Thread.currentThread().getName());
                clients.forEach(account -> {
                    int dividend = account.getDeposit() / 100;
                    account.putMoney(dividend);

                });
                semaphore.release();
            } catch (Exception e) {
                e.printStackTrace();
                semaphore.release();
            }
        }
    }

    public static void test(int x, int y) {
        System.out.println("Test result : " + x / y);
    }
}
