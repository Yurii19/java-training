package hibernate.services;

import hibernate.models.Atm;
import hibernate.models.Client;

import java.util.List;

public class ClientService extends Service<Client> {

    public ClientService() {
        super(Client.class);
    }

    public List<Client> getAllClients() {
        return dao.getAll();
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
