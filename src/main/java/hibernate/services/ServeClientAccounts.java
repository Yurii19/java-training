package hibernate.services;

import java.util.concurrent.Semaphore;

public class ServeClientAccounts {
    public final Semaphore semaphore;

    public ServeClientAccounts() {
        this.semaphore = new Semaphore(100);
    }

    public void startDeposits() {
        ServeAccounts raiseDeposit = new ServeAccounts(semaphore, 10000, "profit");
        ServeAccounts getPayment = new ServeAccounts(semaphore, 60000, "payment");
        Thread multiply = new Thread(raiseDeposit, "multiply");
        Thread paying = new Thread(getPayment, "paying");
        multiply.setDaemon(true);
        paying.setDaemon(true);
        multiply.start();
        paying.start();
    }
}
