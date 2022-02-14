package hibernate.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "operations")
@Getter
@NoArgsConstructor
@RequiredArgsConstructor
public class Operation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "client_id")
    @NonNull
    private Client client;

    @ManyToOne
    @JoinColumn(name = "atm_id")
    @NonNull
    private Atm atm;

    @NonNull
    private OperationType type;

    @NonNull
    private long amount;

//    public Operation(){}
//
//    public Operation(Client client, Atm atm, OperationType type, long amount) {
//        this.client = client;
//        this.atm = atm;
//        this.type = type;
//        this.amount = amount;
//    }
}