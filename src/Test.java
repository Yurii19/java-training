import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Test {

    static Map<Integer, Integer> billsBox = Map.of(1, 0, 2, 0, 5, 0, 10, 0, 20, 0, 100, 0);
   static LinkedHashMap<Integer, Integer> billsBox3 = new LinkedHashMap<Integer, Integer>(billsBox);
    static LinkedHashMap<Integer, Integer> map2 = new LinkedHashMap<Integer, Integer>() {{
        put(1, 0);
        put(2, 0);
        put(5, 0);
        put(10, 0);
        put(20, 0);
        put(100, 0);
    }};

    public static void printMap() {
        // Map<Integer, Integer> billsBox2= Stream.of(billsBox).filter(el ->el.getKey() )
        Map<Integer, Integer> billsBox2 = billsBox.entrySet().stream().filter(el -> el.getKey() != 100).collect(
                Collectors.toMap(p -> p.getKey(), p -> p.getValue()));

        LinkedHashMap<Integer, Integer> map = new LinkedHashMap<Integer, Integer>();
        map.put(1,0);
        map.put(2,0);
        map.put(5,0);
        map.put(10,0);
        map.put(20,0);
        map.put(100,0);

    //    Integer[] rr = map2.keySet().stream().map(el -> el).collect()
        List array = new ArrayList(map2.keySet());
//        for (Map.Entry<Integer, Integer> entry : billsBox3.entrySet()) {
//            System.out.println(" -> " + entry);
//        }

        map2.entrySet().forEach(el -> System.out.println(el.getKey()));
    }
}
