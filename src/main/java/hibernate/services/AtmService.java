package hibernate.services;

import hibernate.models.Atm;
import hibernate.models.Client;
import hibernate.models.Statistic;

public class AtmService extends Service {
    private final int ID;
    private Atm atm;
    private ClientInterface clientInterface;


    public AtmService(int id) {
        super(Atm.class);
        this.ID = id;
    }

    private void launchAtm() {
        atm = (Atm) get(ID);
        if (atm != null) {
            this.clientInterface = new ClientInterface(atm, this);
        } else {
            this.clientInterface = new ClientInterface(new Atm(), this);
        }
    }

    public void ServeClient() {
        launchAtm();
        clientInterface.askUser();
    }

    public void updateAtm(){
        this.update(atm);
        StatisticService statisticService = new StatisticService(Statistic.class);
        //Statistic statistic = Statistic
    }

}
