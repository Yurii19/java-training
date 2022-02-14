package hibernate.models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "clients")
@Getter
@Setter
@Data
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    private long account;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Operation> operations = new ArrayList<>();

    public Client() {
    }

    public Client(String name) {
        this.name = name;
    }

    public long claim(Atm atm, int amount) {
        long effectiveAmount = Math.min(account, amount);
        account -= effectiveAmount;
        Operation operation = new Operation(this, atm, OperationType.GET, effectiveAmount);
        operations.add(operation);
        return effectiveAmount;
    }

    public void deposit(Atm atm, int amount) {
        account += amount;
        Operation operation = new Operation(this, atm, OperationType.PUT, amount);
        operations.add(operation);
    }
}
