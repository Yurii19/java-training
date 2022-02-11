package hibernate.models;

import javax.persistence.*;

@Entity
@Table(name = "operations")

public class Operation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @ManyToOne
    @JoinColumn(name = "atm_id")
    private Atm atm;

    private OperationType type;

    private long amount;

    public Operation(){}

    public Operation(Client client, Atm atm, OperationType type, long amount) {
        this.client = client;
        this.atm = atm;
        this.type = type;
        this.amount = amount;
    }
}
