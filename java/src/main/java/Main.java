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
        LinkedList<LinkedList<String>> splitInGroups = example.stream().reduce(emptyList, Calories::group, (l, i) -> l);
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

    public static List<String> run05(String fileName) throws URISyntaxException, IOException {
        List<String> overview = FileHandler.readFileAsStringList(fileName);
        CargoCrane9000 cargoCrane9000 = new CargoCrane9000(overview);
        cargoCrane9000.runCommands();
        String sum1 = cargoCrane9000.getUpperCrates();

        CargoCrane9001 cargoCrane9001 = new CargoCrane9001(overview);
        cargoCrane9001.enableAirConditioning();
        cargoCrane9001.moveLeatherSeat();
        cargoCrane9001.takeCupFromCupHolder();
        cargoCrane9001.runCommands();
        String sum2 = cargoCrane9001.getUpperCrates();
        return Arrays.asList(sum1, sum2);
    }

    public static List<Integer> run06(String fileName) throws URISyntaxException, IOException {
        List<String> signals = FileHandler.readFileAsStringList(fileName);
        int sum1 = signals.stream().mapToInt(s -> CommunicationSubroutine.getFirstMarker(s, MarkerType.Package)).sum();
        int sum2 = signals.stream().mapToInt(s -> CommunicationSubroutine.getFirstMarker(s, MarkerType.Message)).sum();
        return Arrays.asList(sum1, sum2);
    }

    public static List<Integer> run07(String fileName) throws URISyntaxException, IOException {
        List<String> output = FileHandler.readFileAsStringList(fileName);
        Directory root = FileSystemService.build(output);
        int sum1 = FileSystemService.getTotalSize(root);
        int sum2 = FileSystemService.deleteSmallestDirectory(root);
        return Arrays.asList(sum1, sum2);
    }

    public static List<Integer> run08(String fileName) throws URISyntaxException, IOException {
        List<String> input = FileHandler.readFileAsStringList(fileName);
        Forest forest = new Forest(input);
        int sum1 = forest.countVisibleTrees();
        int sum2 = forest.getTreeWithHighestScenicScore().getScenicScore();

        return Arrays.asList(sum1, sum2);
    }

    public static List<Integer> run09(String fileName) throws URISyntaxException, IOException {
        List<String> input = FileHandler.readFileAsStringList(fileName);
        List<Move> moves = input.stream().map(Move::new).toList();
        int length = 10;
        List<Knot> rope = new ArrayList<>(length);
        for (int i = 0; i < length; i++) {
            rope.add(i, i == 0 ? new Head() : new Tail(rope.get(i - 1)));
        }
        moves.forEach((m) -> {
            for (int i = 0; i < m.steps; i++) {
                rope.forEach(knot -> knot.makeMove(m.direction));
            }
        });
        int sum1 = ((Tail) rope.get(1)).getVisited();
        int sum2 = ((Tail) rope.get(rope.size() - 1)).getVisited();
        return Arrays.asList(sum1, sum2);
    }
}
