import hibernate.models.Atm;
import hibernate.models.Statistic;
import hibernate.services.AtmService;
import hibernate.services.StatisticService;

public class Main {

    public static void main(String args[]) {
        AtmService atmService = new AtmService();
        Atm atm = (Atm) atmService.get(1);
        atmService.ServeClient(atm);
    }
}