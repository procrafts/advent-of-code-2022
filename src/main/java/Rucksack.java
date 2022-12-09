import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

public class Rucksack {
    public static List<String> getCompartments(String rucksack) {
        LinkedList<String> compartments = new LinkedList<>();
        int length = rucksack.length();
        int half = length / 2;
        compartments.add(rucksack.substring(0, half));
        compartments.add(rucksack.substring(half, length));
        return compartments.stream().toList();
    }

    public static List<Character> getBadgeGroups(List<String> rucksacks) {
        List<Character> badgeItems = new LinkedList<>();
        for (int i = 0; i < rucksacks.size(); i += 3) {
            badgeItems.add(findBadgeItem(rucksacks.get(i), rucksacks.get(i + 1), rucksacks.get(i + 2)));
        }
        return badgeItems;
    }

    public static int findItemNumber(Character character) {
        if (Character.isUpperCase(character)) {
            return (int) character - 38;
        }
        return (int) character - 96;
    }

    public static Character findWrongItem(List<String> compartments) {
        for (Character i : compartments.get(0).toCharArray()) {
            if (compartments.get(1).indexOf(i) != -1) {
                return i;
            }
        }
        throw new NoSuchElementException("could not find wrong ordered item");
    }

    private static Character findBadgeItem(String r1, String r2, String r3) {
        for (Character i : r1.toCharArray()) {
            if (r2.indexOf(i) != -1 && r3.indexOf(i) != -1) {
                return i;
            }
        }
        throw new NoSuchElementException("could not find badge item");
    }
}
