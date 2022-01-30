import java.util.List;

public class MultiplyDeposit implements Runnable {
    private final List<Account> clients ;

    public MultiplyDeposit(List<Account> clients) {
        this.clients = clients;
    }


    @Override
    public void run() {
        synchronized (clients) {
            while (true) {

                try {
                    Thread.sleep(10000);
                    clients.forEach(account->{
                       // System.out.println("^^^ "+account.getDeposit());
                        int dividend = account.getDeposit() / 100;
                        account.putMoney(dividend);
                    });


                } catch (Exception e) {
                    e.printStackTrace();
                    return;
                }
            }
        }
    }

    public static void test(int x, int y) {
        System.out.println("Test result : " + x / y);
    }
}
