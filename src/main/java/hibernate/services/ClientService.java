package hibernate.services;

import hibernate.dao.Dao;
import hibernate.models.Atm;
import hibernate.models.Client;

public class ClientService extends Service<Client> {

    public ClientService() {
        super(Client.class);
    }

    public long claim(Client client, Atm atm, int amount) {
        long sum = client.claim(atm, amount);
        update(client);
        return sum;
    }

    public void deposit(Client client, Atm atm, int amount) {
        client.claim(atm, amount);
        update(client);
    }
}
