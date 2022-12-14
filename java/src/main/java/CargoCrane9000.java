import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class CargoCrane9000 {
    protected final List<LinkedList<String>> stacks;
    protected final List<CraneCommand> commands;

    public CargoCrane9000(List<String> overview) {
        stacks = getEmptyStacks(overview);
        placeCrates(overview);
        commands = getCommands(overview);
    }

    public void runCommands() {
        for (CraneCommand c : commands) {
            for (int i = 0; i < c.amount; i++) {
                String crate = stacks.get(c.from).removeLast();
                stacks.get(c.to).addLast(crate);
            }
        }
    }

    public String getUpperCrates() {
        return stacks.stream().map(LinkedList::getLast).collect(Collectors.joining());
    }

    protected void placeCrates(List<String> overview) {
        for (String s : overview) {
            for (int j = 1; j < s.length(); j += 4) {
                if (s.charAt(j) == '1') {
                    return;
                }
                int pos = (j - 1) == 0 ? 0 : (j - 1) / 4;
                String crate = String.valueOf(s.charAt(j));
                if (!crate.isBlank()) {
                    stacks.get(pos).addFirst(crate);
                }
            }
        }
    }

    protected List<LinkedList<String>> getEmptyStacks(List<String> overview) {
        for (String s : overview) {
            if (s.charAt(1) == '1') {
                return Arrays.stream(s.split(" ")).filter(x -> !x.isEmpty()).map(x -> new LinkedList<String>()).toList();
            }
        }
        return null;
    }

    protected List<CraneCommand> getCommands(List<String> overview) {
        List<CraneCommand> craneCommands = new LinkedList<>();
        for (String value : overview) {
            if (!value.isEmpty() && value.charAt(0) == 'm') {
                List<String> commandParts = Arrays.stream(value.split(" ")).filter(s -> s.matches("[0-9]+")).toList();
                craneCommands.add(new CraneCommand(
                        Integer.parseInt(commandParts.get(0)),
                        Integer.parseInt(commandParts.get(1)),
                        Integer.parseInt(commandParts.get(2))
                ));
            }
        }
        return craneCommands;
    }
}