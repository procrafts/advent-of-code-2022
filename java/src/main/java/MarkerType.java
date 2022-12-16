public enum MarkerType {
    Package(4),
    Message(14);

    public final int length;

    MarkerType(int i) {
        length = i;
    }
}
