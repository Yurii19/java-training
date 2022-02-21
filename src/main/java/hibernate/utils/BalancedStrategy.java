package hibernate.utils;

import hibernate.models.Atm;
import hibernate.models.OperationType;
import hibernate.models.Statistic;
import hibernate.services.StatisticService;

import java.util.LinkedHashMap;
import java.util.Map;

public class BalancedStrategy extends Strategy {

    private final LinkedHashMap<Integer, Integer> billsBox;
    private final Atm atm;

    public BalancedStrategy(Atm atm) {
        this.atm = atm;
        this.billsBox = atm.getSlots();
    }

    @Override
    public void giveMoney(int amount) {
        LinkedHashMap<Integer, Integer> billsBoxCopy = new LinkedHashMap<>(billsBox);
        int change = amount;
        LinkedHashMap<Integer, Integer> result = new LinkedHashMap<>();
        while (true) {
            int copyOfChange = change;
            for (Map.Entry<Integer, Integer> entry : billsBoxCopy.entrySet()) {
                int nominal = entry.getKey();
                if (change > nominal) {
                    change = change - nominal;
                    billsBoxCopy.put(nominal, (entry.getValue() - 1));
                }
            }
            if (change == 0) {
                System.out.println(makeGiveReport(billsBox, billsBoxCopy));
                atm.updateSlots(billsBoxCopy);
                LinkedHashMap<Integer, Integer> diff = new LinkedHashMap<>() ;
                billsBox.forEach((key, value) -> diff.put(key, value - billsBoxCopy.get(key)));
                StatisticService statisticService = new StatisticService(Statistic.class);
                Statistic theStat = (Statistic) statisticService.get((int) atm.getId());
                theStat.updateSlots(diff, OperationType.GET);
                statisticService.update(theStat);
                break;
            }
            if (change == copyOfChange) {
                System.out.println("There are no enough Bills !");
                atm.updateSlots(null);
            }
        }
        return ;
    }

}
