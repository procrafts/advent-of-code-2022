import java.util.LinkedList;

public class Calories {
    public static LinkedList<LinkedList<String>> group(LinkedList<LinkedList<String>> list, String s) {
        if (s.equals("")) {
            list.add(new LinkedList<>());
        } else {
            list.getLast().add(s);
        }
        return list;
    }
}
