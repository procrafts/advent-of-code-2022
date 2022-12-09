import java.util.LinkedList;
import java.util.List;

public class Directory extends FileSystemNode {
    protected LinkedList<FileSystemNode> children = new LinkedList<>();

    public Directory(String name) {
        super(name);
    }

    @Override
    public int getSize() {
        return children.stream().mapToInt(FileSystemNode::getSize).sum();
    }

    public List<FileSystemNode> getChildren() {
        return children;
    }

    public void addChild(FileSystemNode child) {
        children.add(child);
    }

    public FileSystemNode getChild(String name) {
        List<FileSystemNode> matches = children.stream().filter(c -> c.name.equals(name)).toList();
        if (matches.size() != 0) {
            return matches.get(0);
        }
        return null;
    }
}

