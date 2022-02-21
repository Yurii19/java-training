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

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "atm_id")
    @NonNull
    private Atm atm;

    @NonNull
    private OperationType type;

    @NonNull
    private long amount;

}
