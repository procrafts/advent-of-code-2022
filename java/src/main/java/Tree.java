public class Tree {
    public final int height;
    private boolean visible = false;
    private int scenicScore = 1;

    public Tree(int height) {
        this.height = height;
    }

    public void setVisibility(boolean isVisible) {
        if (isVisible) {
            visible = true;
        }
    }

    public boolean isVisible() {
        return visible;
    }

    public void improveScenicScore(int value) {
        scenicScore *= value;
    }

    public int getScenicScore() {
        return scenicScore;
    }
}
