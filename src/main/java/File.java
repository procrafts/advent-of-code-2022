public class File extends FileSystemNode {
    protected int size;

    File(String name, int size) {
        super(name);
        this.size = size;
    }

    @Override
    int getSize() {
        return size;
    }
}
