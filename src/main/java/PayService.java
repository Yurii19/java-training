import java.util.List;

public class PayService implements Runnable {
    private final List<Account> clients;

    public PayService(List<Account> clients) {
        this.clients = clients;
    }

    @Override
    public void run() {
        synchronized (clients) {
            while (true) {
                try {
                    Thread.sleep(60000);

                    clients.forEach(account -> {
                        if (account.getDeposit() >= 10) {
                            account.takeMoney(10);
                        }
                    });

                } catch (Exception e) {
                    e.printStackTrace();
                    return;
                }
            }
        }
    }

//    public static void test(int x, int y) {
//        System.out.println("Test result : " + x / y);
//    }
}
