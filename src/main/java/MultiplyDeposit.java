public class MultiplyDeposit implements Runnable {
    private Account theAccount;

    @Override
    public void run() {
        synchronized (theAccount) {
            while (true) {
                try {
                    Thread.sleep(10000);
                    int dividend = theAccount.getDeposit() / 100;
                    theAccount.putMoney(dividend);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void test(int x, int y) {
        System.out.println("Test result : " + x / y);
    }
}
