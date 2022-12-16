public class Head extends Knot {

    @Override
    public void makeMove(Direction direction) {
        switch (direction) {
            case Up -> position.y++;
            case Right -> position.x++;
            case Down -> position.y--;
            case Left -> position.x--;
        }
    }
}
