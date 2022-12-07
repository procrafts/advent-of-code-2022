import java.util.LinkedList;
import java.util.List;

public class FileSystemService {
    static Directory build(List<String> output) {
        FileSystemNode root = new Directory("/");
        for (String line : output) {
            if (line.startsWith("$ cd ..")) {
                root = root.getParent();
            } else if (line.startsWith("$ cd ")) {
                String name = line.substring(5);
                Directory parent = (Directory) root;
                FileSystemNode child = parent.getChild(name);
                if (child == null) {
                    child = new Directory(name);
                    parent.addChild(child);
                    child.setParent(parent);
                }
                root = child;
            } else if (line.matches("\\d+ \\w+.?\\w*")) {
                String[] s = line.split(" ");
                String name = s[1];
                Directory parent = (Directory) root;
                if (parent.getChild(name) == null) {
                    int size = Integer.parseInt(s[0]);
                    File child = new File(name, size);
                    child.setParent(root);
                    parent.addChild(child);
                }
            }
        }

        while (!root.name.equals("/")) {
            root = root.getParent();
        }

        return (Directory) root;
    }

    static int getTotalSize(Directory node) {
        int sum = 0;

        if (node.getSize() <= 100000) {
            sum += node.getSize();
        }

        for (FileSystemNode c : node.getChildren()) {
            if (c instanceof Directory) {
                sum += getTotalSize((Directory) c);
            }
        }
        return sum;
    }

    static int deleteSmallestDirectory(Directory node) {
        List<Integer> deletableDirectorySizes = new LinkedList<>();
        int freeSpace = 70000000 - node.getSize();
        int spaceToFreeUp = 30000000 - freeSpace;
        findDeletableDirectory(node, spaceToFreeUp, deletableDirectorySizes);
        return deletableDirectorySizes.stream().sorted().toList().get(0);
    }

    static void findDeletableDirectory(Directory node, int neededSize, List<Integer> deletableDirectorySizes) {
        int size = node.getSize();
        if (size >= neededSize) {
            deletableDirectorySizes.add(size);
        }
        for (FileSystemNode child : node.children) {
            if (child instanceof Directory) {
                findDeletableDirectory((Directory) child, neededSize, deletableDirectorySizes);
            }
        }
    }
}
