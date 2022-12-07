import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class Directory extends FileSystemNode {
    LinkedList<FileSystemNode> children = new LinkedList<>();

    Directory(String name) {
        super(name);
    }

    @Override
    int getSize() {
        return children.stream().mapToInt(FileSystemNode::getSize).sum();
    }

    List<FileSystemNode> getChildren() {
        return children;
    }

    void addChild(FileSystemNode child) {
        children.add(child);
    }

    FileSystemNode getChild(String name) {
        List<FileSystemNode> matches = children.stream().filter(c -> Objects.equals(c.name, name)).toList();
        if (matches.size() != 0) {
            return matches.get(0);
        }
        return null;
    }
}

