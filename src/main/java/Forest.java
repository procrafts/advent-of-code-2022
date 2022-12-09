import java.util.List;

public class Forest {
    private final Tree[][] forest;

    public Forest(List<String> input) {
        forest = new Tree[input.get(0).length()][input.size()];
        for (int x = 0; x < input.get(0).length(); x++) {
            for (int y = 0; y < input.size(); y++) {
                int height = Integer.parseInt(Character.toString(input.get(y).charAt(x)));
                Tree tree = new Tree(height);
                forest[x][y] = tree;
            }
        }
        for (int x = 0; x < input.get(0).length(); x++) {
            for (int y = 0; y < input.size(); y++) {
                setVisibility(x, y, forest[x][y]);
                improveScenicScore(x, y, forest[x][y]);
            }
        }
    }

    private void setVisibility(int x, int y, Tree tree) {
        tree.setVisibility(getVisibilityVertical(x, y, 0, -1, tree));
        tree.setVisibility(getVisibilityVertical(x, y, forest[x].length - 1, 1, tree));
        tree.setVisibility(getVisibilityHorizontal(x, y, 0, -1, tree));
        tree.setVisibility(getVisibilityHorizontal(x, y, forest.length - 1, 1, tree));
    }

    private void improveScenicScore(int x, int y, Tree tree) {
        tree.improveScenicScore(getScenicScoreVertical(x, y, 0, -1, tree));
        tree.improveScenicScore(getScenicScoreVertical(x, y, forest[x].length - 1, 1, tree));
        tree.improveScenicScore(getScenicScoreHorizontal(x, y, 0, -1, tree));
        tree.improveScenicScore(getScenicScoreHorizontal(x, y, forest.length - 1, 1, tree));
    }

    private boolean getVisibilityVertical(int x, int y, int border, int step, Tree tree) {
        if (y == border) {
            return true;
        }
        int pos = y + step;
        while (pos != border + step) {
            Tree otherTree = forest[x][pos];
            if (otherTree.height >= tree.height) {
                return false;
            }
            pos += step;
        }
        return true;
    }

    private boolean getVisibilityHorizontal(int x, int y, int border, int step, Tree tree) {
        if (x == border) {
            return true;
        }
        int pos = x + step;
        while (pos != border + step) {
            Tree otherTree = forest[pos][y];
            if (otherTree.height >= tree.height) {
                return false;
            }
            pos += step;
        }
        return true;
    }

    private int getScenicScoreVertical(int x, int y, int border, int step, Tree tree) {
        int score = 0;
        if (y == border) {
            return score;
        }
        int pos = y + step;
        while (pos != border + step) {
            Tree otherTree = forest[x][pos];

            score++;
            if (otherTree.height >= tree.height) {
                return score;
            }
            pos += step;
        }
        return score;
    }

    private int getScenicScoreHorizontal(int x, int y, int border, int step, Tree tree) {
        int score = 0;
        if (x == border) {
            return score;
        }
        int pos = x + step;
        while (pos != border + step) {
            Tree otherTree = forest[pos][y];
            score++;
            if (otherTree.height >= tree.height) {
                return score;
            }
            pos += step;
        }
        return score;
    }

    public int countVisibleTrees() {
        int count = 0;
        for (Tree[] line : forest) {
            for (Tree tree : line) {
                if (tree.isVisible()) {
                    count++;
                }
            }
        }
        return count;
    }

    public Tree getTreeWithHighestScenicScore() {
        Tree result = null;
        for (Tree[] line : forest) {
            for (Tree tree : line) {
                if (result == null || tree.getScenicScore() > result.getScenicScore()) {
                    result = tree;
                }
            }
        }
        return result;
    }
}
