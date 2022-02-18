package hibernate.models;

import lombok.Data;
import lombok.NonNull;

import javax.persistence.*;
import java.util.LinkedHashMap;

import static hibernate.models.OperationType.GET;

@Entity
@Table(name = "statistics")
@Data
public class Statistic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "atm_id")
    @NonNull
    private Atm atm;

    @Column(name = "slot_1_add")
    private int slot1Add;
    @Column(name = "slot_1_sub")
    private int slot1Sub;
    @Column(name = "slot_2_add")
    private int slot2Add;
    @Column(name = "slot_2_sub")
    private int slot2Sub;
    @Column(name = "slot_5_add")
    private int slot5Add;
    @Column(name = "slot_5_sub")
    private int slot5Sub;
    @Column(name = "slot_10_add")
    private int slot10Add;
    @Column(name = "slot_10_sub")
    private int slot10Sub;
    @Column(name = "slot_20_add")
    private int slot20Add;
    @Column(name = "slot_20_sub")
    private int slot20Sub;
    @Column(name = "slot_50_add")
    private int slot50Add;
    @Column(name = "slot_50_sub")
    private int slot50Sub;
    @Column(name = "slot_100_add")
    private int slot100Add;
    @Column(name = "slot_100_sub")
    private int slot100Sub;

    public Statistic() {
    }

    public void updateSlots(LinkedHashMap<Integer, Integer> slots, OperationType operationType) {
        slots.forEach((key, value) -> updateSlot(key, value, operationType));
    }

    public void updateSlot(int nominal, int amount, OperationType operationType) {
        switch (nominal) {
            case 1:
                if (operationType == GET) {
                    setSlot1Add(amount);
                } else {
                    setSlot1Sub(amount);
                }
                break;
            case 2:
                if (operationType == GET) {
                    setSlot2Add(amount);
                } else {
                    setSlot2Sub(amount);
                }
                break;
            case 5:
                if (operationType == GET) {
                    setSlot5Add(amount);
                } else {
                    setSlot5Sub(amount);
                }
                break;
            case 10:
                if (operationType == GET) {
                    setSlot10Add(amount);
                } else {
                    setSlot10Sub(amount);
                }
                break;
            case 20:
                if (operationType == GET) {
                    setSlot20Add(amount);
                } else {
                    setSlot20Sub(amount);
                }
                break;
            case 50:
                if (operationType == GET) {
                    setSlot50Add(amount);
                } else {
                    setSlot50Sub(amount);
                }
                break;
            case 100:
                if (operationType == GET) {
                    setSlot100Add(amount);
                } else {
                    setSlot100Sub(amount);
                }
                break;
            default:
                break;
        }
    }

}
