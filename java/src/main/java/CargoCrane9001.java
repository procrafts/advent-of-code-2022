import java.util.List;
import java.util.Stack;

public class CargoCrane9001 extends CargoCrane9000 {
    public CargoCrane9001(List<String> overview) {
        super(overview);
    }

    @Override
    public void runCommands() {
        for (CraneCommand c : commands) {
            Stack<String> crates = new Stack<>();
            for (int i = 0; i < c.amount; i++) {
                String crate = stacks.get(c.from).removeLast();
                crates.push(crate);
            }
            for (int i = 0; i < c.amount; i++) {
                String crate = crates.pop();
                stacks.get(c.to).addLast(crate);
            }
        }
    }

    public void enableAirConditioning() {

    }

    public void moveLeatherSeat() {

    }

    public void takeCupFromCupHolder() {

    }
}