package hibernate.models;

import lombok.Data;

import javax.persistence.*;
import java.util.LinkedHashMap;
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
    private List<Operation> operations;

    public void updateSlots(LinkedHashMap<Integer, Integer> newBillsSet) {
        newBillsSet.entrySet().forEach(e -> setAmountBills(e.getKey(), e.getValue()));
    }

    public void setAmountBills(int nominal, int amount) {
        switch (nominal) {
            case 1:
                setSlot1Uah(amount);
                break;
            case 2:
                setSlot2Uah(amount);
                break;
            case 5:
                setSlot5Uah(amount);
                break;
            case 10:
                setSlot10Uah(amount);
                break;
            case 20:
                setSlot20Uah(amount);
                break;
            case 50:
                setSlot50Uah(amount);
                break;
            case 100:
                setSlot100Uah(amount);
                break;
            default:
                break;
        }
    }

    public int getAmountBills(int nominal) {
        switch (nominal) {
            case 1:
                return getSlot1Uah();
            case 2:
                return getSlot2Uah();
            case 5:
                return getSlot5Uah();
            case 10:
                return getSlot10Uah();
            case 20:
                return getSlot20Uah();
            case 50:
                return getSlot50Uah();
            case 100:
                return getSlot100Uah();
            default:
                return -1;
        }
    }
}
