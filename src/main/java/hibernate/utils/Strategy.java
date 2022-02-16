package hibernate.utils;

import java.util.LinkedHashMap;

public abstract class Strategy {
      protected abstract void giveMoney(int amount);

      /**
       * @param billsBoxBefore array contains information about different ATM bills before operation
       * @param billsBoxAfter  newArray array contains information about different ATM bills after operation
       * @return string the contains information about how much and what nominal where given
       */
      static String makeGiveReport(LinkedHashMap<Integer, Integer> billsBoxBefore, LinkedHashMap<Integer, Integer> billsBoxAfter) {
            StringBuilder report = new StringBuilder("");
            billsBoxBefore.entrySet().forEach(el -> {
                  int diff = el.getValue() - billsBoxAfter.get(el.getKey());
                  if (diff > 0) {
                        String logRow = " " + diff + " bills of " + el.getKey() + ";";
                        report.append(logRow);
                  }
            });
            return "Were given: " + report.toString();
      }
}
