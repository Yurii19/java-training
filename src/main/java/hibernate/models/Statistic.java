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

    public Statistic(Atm atm) {
        this.atm = atm;
    }

    public Statistic() {

    }

    public void updateSlots(LinkedHashMap<Integer, Integer> slots, OperationType operationType) {
        slots.forEach((key, value) -> updateSlot(key, value, operationType));
    }

    public void updateSlot(int nominal, int amount, OperationType operationType) {
        switch (nominal) {
            case 1:
                if (operationType == GET) {
                    setSlot1Sub(getSlot1Sub() + amount);
                } else {
                    setSlot1Add(getSlot1Add() + amount);
                }
                break;
            case 2:
                if (operationType == GET) {
                    setSlot2Sub(getSlot2Sub() + amount);
                } else {
                    setSlot2Add(getSlot2Add() + amount);
                }
                break;
            case 5:
                if (operationType == GET) {
                    setSlot5Sub(getSlot5Sub() + amount);
                } else {
                    setSlot5Add(getSlot5Add() + amount);
                }
                break;
            case 10:
                if (operationType == GET) {
                    setSlot10Sub(getSlot10Sub() + amount);
                } else {
                    setSlot10Add(getSlot10Add() + amount);
                }
                break;
            case 20:
                if (operationType == GET) {
                    setSlot20Sub(getSlot20Sub() + amount);
                } else {
                    setSlot20Add(getSlot20Add() + amount);
                }
                break;
            case 50:
                if (operationType == GET) {
                    setSlot50Sub(getSlot50Sub() + amount);
                } else {
                    setSlot50Add(getSlot50Add() + amount);
                }
                break;
            case 100:
                if (operationType == GET) {
                    setSlot100Sub(getSlot100Sub() + amount);
                } else {
                    setSlot100Add(getSlot100Add() + amount);
                }
                break;
            default:
                break;
        }
    }

    public void printStatistic(){
        System.out.println(" 1 uah bill :putted - "+ getSlot1Add()+", picked - "+ getSlot1Sub() );
        System.out.println(" 2 uah bill :putted - "+ getSlot2Add()+", picked - "+ getSlot2Sub() );
        System.out.println(" 5 uah bill :putted - "+ getSlot5Add()+", picked - "+ getSlot5Sub() );
        System.out.println(" 10 uah bill :putted - "+ getSlot10Add()+", picked - "+ getSlot10Sub() );
        System.out.println(" 20 uah bill :putted - "+ getSlot20Add()+", picked - "+ getSlot20Sub() );
        System.out.println(" 50 uah bill :putted - "+ getSlot50Add()+", picked - "+ getSlot50Sub() );
        System.out.println(" 100 uah bill :putted - "+ getSlot100Add()+", picked - "+ getSlot100Sub() );
    }

}
