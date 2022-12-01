import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {

    }

    public static int run1(String fileName) throws URISyntaxException, IOException {
        File file = readFile(fileName);
        List<String> example = convertFileToStrings(file);
        LinkedList<LinkedList<String>> emptyList = new LinkedList<>();
        emptyList.add(new LinkedList<>());
        LinkedList<LinkedList<String>> splitInGroups = example.stream().reduce(emptyList, Main::group, (l, i) -> l);
        LinkedList<Integer> summedUpGroups = splitInGroups.stream().map(l -> l.stream().mapToInt(Integer::parseInt).sum()).collect(Collectors.toCollection(LinkedList::new));
        Optional<Integer> a = summedUpGroups.stream().max(Integer::compare);
        return a.get();
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
}
