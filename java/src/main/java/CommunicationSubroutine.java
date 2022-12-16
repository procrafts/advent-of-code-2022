import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public class CommunicationSubroutine {
    public static int getFirstMarker(String signal, MarkerType markerType) {
        int pointer = 0;
        LinkedList<Character> group = new LinkedList<>();
        int marker = 0;
        while (marker == 0 && pointer < signal.length()) {
            char target = signal.charAt(pointer);
            group.add(target);
            if (group.size() > markerType.length) {
                group.poll();
                if (isUnambiguity(group, markerType)) {
                    marker = pointer + 1;
                }
            }
            pointer++;
        }
        return marker;
    }

    private static boolean isUnambiguity(LinkedList<Character> group, MarkerType markerType) {
        Set<Character> result = new HashSet<>(group);
        return result.size() == markerType.length;
    }
}
