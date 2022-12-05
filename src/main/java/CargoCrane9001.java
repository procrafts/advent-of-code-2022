import java.util.List;
import java.util.Stack;

public class CargoCrane9001 extends CargoCrane9000 {
    CargoCrane9001(List<String> overview) {
        super(overview);
    }

    @Override
    public void runCommands() {
        for (CraneCommand c : commands) {
            Stack<String> crates = new Stack<>();
            for (int i = 0; i < c.amount; i++) {
                String cargo = stacks.get(c.from).removeLast();
                crates.push(cargo);
            }
            for (int i = 0; i < c.amount; i++) {
                String cargo = crates.pop();
                stacks.get(c.to).addLast(cargo);
            }
        }
    }

    void enableAirConditioning() {

    }

    void moveLeatherSeat() {

    }

    void takeCupFromCupHolder() {

    }
}