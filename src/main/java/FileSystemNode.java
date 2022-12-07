import java.util.Comparator;

public abstract class FileSystemNode implements Comparator<FileSystemNode> {
    final String name;
    protected FileSystemNode parent;

    FileSystemNode(String name) {
        this.name = name;
    }

    FileSystemNode getParent() {
        return parent;
    }

    void setParent(FileSystemNode parent) {
        this.parent = parent;
    }

    abstract int getSize();

    @Override
    public int compare(FileSystemNode a, FileSystemNode b) {
        return a.getSize() - b.getSize();
    }
}

