import hibernate.models.Atm;
import hibernate.models.Statistic;
import hibernate.services.AtmService;
import hibernate.services.ServeClientAccounts;
import hibernate.services.StatisticService;

public class Main {

    public static void main(String args[]) {
        ServeClientAccounts serveClientAccounts = new ServeClientAccounts();
        serveClientAccounts.startDeposits();
        AtmService atmService = new AtmService();
        Atm atm = (Atm) atmService.get(1);
        atmService.ServeClient(atm);

    }
}