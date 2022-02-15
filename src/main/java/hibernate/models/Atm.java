package hibernate.models;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "atms")
@Data
public class Atm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "slot_1")
    private int slot1Uah;
    @Column(name = "slot_2")
    private int slot2Uah;
    @Column(name = "slot_5")
    private int slot5Uah;
    @Column(name = "slot_10")
    private int slot10Uah;
    @Column(name = "slot_20")
    private int slot20Uah;
    @Column(name = "slot_50")
    private int slot50Uah;
    @Column(name = "slot_100")
    private int slot100Uah;

    @OneToMany(mappedBy = "atm")
    private List<Operation> operations ;

}
