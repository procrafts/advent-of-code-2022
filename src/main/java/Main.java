import java.io.IOException;
import java.net.URISyntaxException;
import java.util.*;
import java.util.stream.Collectors;


public class Main {


    public static void main(String[] args) {

    }

    public static List<Integer> run01(String fileName) throws URISyntaxException, IOException {
        List<String> example = FileHandler.readFileAsStringList(fileName);
        LinkedList<LinkedList<String>> emptyList = new LinkedList<>();
        emptyList.add(new LinkedList<>());
        LinkedList<LinkedList<String>> splitInGroups = example.stream().reduce(emptyList, Main::group, (l, i) -> l);
        LinkedList<Integer> summedUpGroups = splitInGroups.stream().map(l -> l.stream().mapToInt(Integer::parseInt).sum()).collect(Collectors.toCollection(LinkedList::new));
        List<Integer> sorted = new ArrayList<>(summedUpGroups.stream().sorted().toList());
        Collections.reverse(sorted);
        int sumOfTopOne = sorted.get(0);
        int sumOfTopThree = sorted.subList(0, 3).stream().mapToInt(x -> x).sum();
        return Arrays.asList(sumOfTopOne, sumOfTopThree);
    }

    public static List<Integer> run02(String fileName) throws URISyntaxException, IOException {
        List<String> example = FileHandler.readFileAsStringList(fileName);
        var matches = example.stream().map(s -> Arrays.stream(s.split(" ")).toList()).toList();
        var scoreIfSymbol = matches.stream().map(RockPaperScissors::calculateScore).reduce(0, Integer::sum);
        var scoreIfRecommendedPlay = matches.stream().map(RockPaperScissors::playAsRecommended).reduce(0, Integer::sum);
        return Arrays.asList(scoreIfSymbol, scoreIfRecommendedPlay);
    }

    public static List<Integer> run03(String fileName) throws URISyntaxException, IOException {
        List<String> rucksacks = FileHandler.readFileAsStringList(fileName);
        List<List<String>> withCompartments = rucksacks.stream().map(Rucksack::getCompartments).toList();
        List<Character> wrongItems = withCompartments.stream().map(Rucksack::findWrongItem).toList();
        List<Integer> itemNumbers = wrongItems.stream().map(Rucksack::findItemNumber).toList();
        int sum = itemNumbers.stream().mapToInt(x -> x).sum();

        List<Character> badgeGroups = Rucksack.getBadgeGroups(rucksacks);
        List<Integer> badgeItemNumbers = badgeGroups.stream().map(Rucksack::findItemNumber).toList();
        int sum2 = badgeItemNumbers.stream().mapToInt(x -> x).sum();

        return Arrays.asList(sum, sum2);
    }

    public static List<Integer> run04(String fileName) throws URISyntaxException, IOException {
        List<String> assignments = FileHandler.readFileAsStringList(fileName);
        List<List<List<String>>> pairDescription = RoomManagement.getRoomDescription(assignments);
        List<Integer> areFullyContained = pairDescription.stream().map(RoomManagement::isFullyContained).toList();
        int sum1 = areFullyContained.stream().mapToInt(x -> x).sum();

        List<Integer> arePartlyContained = pairDescription.stream().map(RoomManagement::isPartlyContained).toList();
        int sum2 = arePartlyContained.stream().mapToInt(x -> x).sum();

        return Arrays.asList(sum1, sum2);
    }

    public static LinkedList<LinkedList<String>> group(LinkedList<LinkedList<String>> list, String s) {
        if (s.equals("")) {
            list.add(new LinkedList<>());
        } else {
            list.getLast().add(s);
        }
        return list;
    }

}
