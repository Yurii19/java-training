package hibernate.models;

import lombok.Data;

import javax.persistence.*;
import java.util.Arrays;
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

    @OneToMany(mappedBy = "atm", fetch = FetchType.EAGER)
    private List<Operation> operations;

    public void updateSlots(LinkedHashMap<Integer, Integer> newBillsSet) {
        if (newBillsSet == null) {
            System.err.println("Not enough bills in the ATM");
            return;
        }
        newBillsSet.forEach((key, value) -> setAmountBills(key, value));
    }

    public LinkedHashMap<Integer, Integer> getSlots() {
        int[] keys = new int[]{1, 2, 5, 10, 20, 50, 100};
        LinkedHashMap<Integer, Integer> slots = new LinkedHashMap<>();
        Arrays.stream(keys).forEach(e -> slots.put(e, getAmountBills(e)));
        return slots;
    }

    public void setAmountBills(int nominal, int amount) {
        switch (nominal) {
            case 1:
                setSlot1Uah(getSlot1Uah() + amount);
                break;
            case 2:
                setSlot2Uah(getSlot2Uah() + amount);
                break;
            case 5:
                setSlot5Uah(getSlot5Uah() + amount);
                break;
            case 10:
                setSlot10Uah(getSlot10Uah() + amount);
                break;
            case 20:
                setSlot20Uah(getSlot20Uah() + amount);
                break;
            case 50:
                setSlot50Uah(getSlot50Uah() + amount);
                break;
            case 100:
                setSlot100Uah(getSlot100Uah() + amount);
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
