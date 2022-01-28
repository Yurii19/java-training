public class PayService implements Runnable {
    private Account theAccount;

    @Override
    public void run() {
        synchronized (theAccount) {
            while (true) {
                try {
                    Thread.sleep(60000);
                    if (theAccount.getDeposit() >= 10) {
                        theAccount.takeMoney(10);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

//    public static void test(int x, int y) {
//        System.out.println("Test result : " + x / y);
//    }
}
