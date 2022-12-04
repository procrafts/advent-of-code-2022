import java.util.Arrays;
import java.util.List;

public class RoomManagement {
    public static List<List<List<String>>> getRoomDescription(List<String> assignments) {
        return assignments.stream().map(a -> Arrays.stream(a.split(",")).map(b -> Arrays.stream(b.split("-")).toList()).toList()).toList();
    }

    public static int isFullyContained(List<List<String>> lists) {
        int aLeft = Integer.parseInt(lists.get(0).get(0));
        int aRight = Integer.parseInt(lists.get(0).get(1));
        int bLeft = Integer.parseInt(lists.get(1).get(0));
        int bRight = Integer.parseInt(lists.get(1).get(1));
        if (aLeft >= bLeft && aRight <= bRight) {
            return 1;
        }
        if (bLeft >= aLeft && bRight <= aRight) {
            return 1;
        }
        return 0;
    }

    public static int isPartlyContained(List<List<String>> lists) {
        int aLeft = Integer.parseInt(lists.get(0).get(0));
        int aRight = Integer.parseInt(lists.get(0).get(1));
        int bLeft = Integer.parseInt(lists.get(1).get(0));
        int bRight = Integer.parseInt(lists.get(1).get(1));
        if (bLeft > aRight || aLeft > bRight) {
            return 0;
        }
        return 1;
    }
}
