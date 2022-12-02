import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    static HashMap<String, Integer> points = new HashMap<>(){{
        put("ROCK", 1);
        put("PAPER", 2);
        put("SCISSORS", 3);
    }};
    static HashMap<String, String> elfHand = new HashMap<>(){{
        put("A", "ROCK");
        put("B", "PAPER");
        put("C", "SCISSORS");
    }};
    static HashMap<String, String> myHand = new HashMap<>(){{
        put("X", "ROCK");
        put("Y", "PAPER");
        put("Z", "SCISSORS");
    }};
    static HashMap<String, String> recommendedPlay = new HashMap<>(){{
        put("X", "LOSE");
        put("Y", "DRAW");
        put("Z", "WIN");
    }};
    public static void main(String[] args) {

    }

    public static List<Integer> run01(String fileName) throws URISyntaxException, IOException {
        File file = readFile(fileName);
        List<String> example = convertFileToStrings(file);
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
        File file = readFile(fileName);
        List<String> example = convertFileToStrings(file);
        var matches = example.stream().map(s -> Arrays.stream(s.split(" ")).toList()).toList();
        var scoreIfSymbol = matches.stream().map(Main::calculateScore).reduce(0, Integer::sum);
        var scoreIfRecommendedPlay = matches.stream().map(Main::playAsRecommended).reduce(0, Integer::sum);
        return Arrays.asList(scoreIfSymbol, scoreIfRecommendedPlay);
    }

    public static LinkedList<LinkedList<String>> group(LinkedList<LinkedList<String>> list, String s) {
        if (s.equals("")) {
            list.add(new LinkedList<>());
        } else {
            list.getLast().add(s);
        }
        return list;
    }


    public static File readFile(String fileName) throws URISyntaxException {
        ClassLoader classLoader = Main.class.getClassLoader();
        URL resource = classLoader.getResource(fileName);
        if (resource == null) {
            throw new IllegalArgumentException("file not found! " + fileName);
        } else {
            return new File(resource.toURI());
        }
    }

    public static List<String> convertFileToStrings(File file) throws IOException {
        List<String> lines;
        lines = Files.readAllLines(file.toPath(), StandardCharsets.UTF_8);
        return lines;
    }

    static int calculateScore(List<String> match) {
        if(myHand.get(match.get(1)).equals("ROCK")) {
            if(elfHand.get(match.get(0)).equals("ROCK")) {
                return 3 + points.get("ROCK");
            } else if(elfHand.get(match.get(0)).equals("PAPER")) {
                return points.get("ROCK");
            } else if(elfHand.get(match.get(0)).equals("SCISSORS")) {
                return 6 + points.get("ROCK");
            }
        } else if(myHand.get(match.get(1)).equals("PAPER")) {
            if(elfHand.get(match.get(0)).equals("ROCK")) {
                return 6 + points.get("PAPER");
            } else if(elfHand.get(match.get(0)).equals("PAPER")) {
                return 3 + points.get("PAPER");
            } else if(elfHand.get(match.get(0)).equals("SCISSORS")) {
                return points.get("PAPER");
            }
        } else if(myHand.get(match.get(1)).equals("SCISSORS")) {
            if(elfHand.get(match.get(0)).equals("ROCK")) {
                return points.get("SCISSORS");
            } else if(elfHand.get(match.get(0)).equals("PAPER")) {
                return 6 + points.get("SCISSORS");
            } else if(elfHand.get(match.get(0)).equals("SCISSORS")) {
                return 3 + points.get("SCISSORS");
            }
        }
        return 0;
    }
    static int playAsRecommended(List<String> match) {
        if(recommendedPlay.get(match.get(1)).equals("LOSE")) {
            if(elfHand.get(match.get(0)).equals("ROCK")) {
                return points.get("SCISSORS");
            } else if(elfHand.get(match.get(0)).equals("PAPER")) {
                return points.get("ROCK");
            } else if(elfHand.get(match.get(0)).equals("SCISSORS")) {
                return points.get("PAPER");
            }
        }
        if(recommendedPlay.get(match.get(1)).equals("DRAW")) {
            if(elfHand.get(match.get(0)).equals("ROCK")) {
                return 3 + points.get("ROCK");
            } else if(elfHand.get(match.get(0)).equals("PAPER")) {
                return 3 + points.get("PAPER");
            } else if(elfHand.get(match.get(0)).equals("SCISSORS")) {
                return 3 + points.get("SCISSORS");
            }
        }
        if(recommendedPlay.get(match.get(1)).equals("WIN")) {
            if(elfHand.get(match.get(0)).equals("ROCK")) {
                return 6 + points.get("PAPER");
            } else if(elfHand.get(match.get(0)).equals("PAPER")) {
                return 6 + points.get("SCISSORS");
            } else if(elfHand.get(match.get(0)).equals("SCISSORS")) {
                return 6 + points.get("ROCK");
            }
        }
        return 0;
    }

}
