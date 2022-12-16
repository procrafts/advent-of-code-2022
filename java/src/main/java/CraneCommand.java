public class CraneCommand {
    public int amount;
    public int from;
    public int to;

    public CraneCommand(int amount, int from, int to) {
        this.amount = amount;
        this.from = from - 1;
        this.to = to - 1;
    }
}
