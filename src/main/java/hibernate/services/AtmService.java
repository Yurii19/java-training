package hibernate.services;

import hibernate.models.Atm;
import hibernate.models.Statistic;

public class AtmService extends Service {
    private Atm atm;
    private ClientInterface clientInterface;

    public AtmService() {
        super(Atm.class);
    }

    private void launchAtm(Atm atm) {
        this.atm = atm;
    }

    public void ServeClient(Atm atm) {
        launchAtm(atm);
        this.clientInterface = new ClientInterface(this.atm, this);
        clientInterface.askUser();
    }

    public Atm createNewWithStatistic(Atm newAtm) {
        this.createNew(newAtm);
        StatisticService statisticService = new StatisticService(Statistic.class);
        statisticService.createNew(new Statistic(newAtm));
        return newAtm;
    }

}
