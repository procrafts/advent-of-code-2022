public class Move {
    public final int steps;
    public Direction direction;

    public Move(String move) {
        String[] s = move.split(" ");
        steps = Integer.parseInt(s[1]);
        switch (s[0]) {
            case "U" -> direction = Direction.Up;
            case "R" -> direction = Direction.Right;
            case "D" -> direction = Direction.Down;
            case "L" -> direction = Direction.Left;
        }
    }
}
