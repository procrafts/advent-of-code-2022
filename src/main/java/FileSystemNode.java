import java.util.Comparator;

public abstract class FileSystemNode implements Comparator<FileSystemNode> {
    public final String name;
    protected FileSystemNode parent;

    public FileSystemNode(String name) {
        this.name = name;
    }

    public FileSystemNode getParent() {
        return parent;
    }

    public void setParent(FileSystemNode parent) {
        this.parent = parent;
    }

    public abstract int getSize();

    @Override
    public int compare(FileSystemNode a, FileSystemNode b) {
        return a.getSize() - b.getSize();
    }
}

