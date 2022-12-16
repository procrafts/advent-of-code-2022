public class Position {
    int x = 0;
    int y = 0;

    @Override
    public String toString() {
        return String.format("%s,%s", x, y);
    }
}
