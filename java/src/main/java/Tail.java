import java.util.HashSet;
import java.util.Set;

public class Tail extends Knot {
    private final Set<String> visited = new HashSet<>();
    protected Position parentPosition;

    public Tail(Knot parent) {
        parentPosition = parent.position;
    }

    public int getVisited() {
        return visited.size();
    }

    @Override
    public void makeMove(Direction direction) {
        react();
        addVisited();
    }

    private void react() {
        Position p = position;
        Position pp = parentPosition;
        if (pp.x == p.x && pp.y == p.y + 2) {
            p.y++;
        } else if ((pp.x > p.x && pp.y > p.y) && !(pp.x == p.x + 1 && pp.y == p.y + 1)) {
            p.x++;
            p.y++;
        } else if (pp.x == p.x + 2 && pp.y == p.y) {
            p.x++;
        } else if ((pp.x > p.x && pp.y < p.y) && !(pp.x == p.x + 1 && pp.y == p.y - 1)) {
            p.x++;
            p.y--;
        } else if (pp.x == p.x && pp.y == p.y - 2) {
            p.y--;
        } else if ((pp.x < p.x && pp.y < p.y) && !(pp.x == p.x - 1 && pp.y == p.y - 1)) {
            p.x--;
            p.y--;
        } else if (pp.x == p.x - 2 && pp.y == p.y) {
            p.x--;
        } else if ((pp.x < p.x && pp.y > p.y) && !(pp.x == p.x - 1 && pp.y == p.y + 1)) {
            p.x--;
            p.y++;
        }
    }

    private void addVisited() {
        visited.add(position.toString());
    }
}
