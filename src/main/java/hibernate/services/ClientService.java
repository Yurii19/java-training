package hibernate.services;

import hibernate.models.Atm;
import hibernate.models.Client;
import hibernate.models.Operation;
import hibernate.models.OperationType;

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
        if (sum > 0){
            Operation operation = new Operation(client, atm, OperationType.GET, amount);
            OperationService operationService = new OperationService();
            operationService.createNew(operation);
            client.addOperation(operation);
            update(client);
        }
        return sum;
    }

    public void deposit(Client client, Atm atm, int amount) {
        Operation operation = new Operation(client, atm, OperationType.PUT, amount);
        OperationService operationService = new OperationService();
        operationService.createNew(operation);
        client.deposit(atm, amount);
        update(client);
    }
}
