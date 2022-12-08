public class Tree {
    final int height;
    private boolean visible = false;
    private int scenicScore = 1;

    public Tree(int height) {
        this.height = height;
    }

    void setVisibility(boolean isVisible) {
        if(isVisible) {
            visible = true;
        }
    }
    boolean isVisible() {
        return visible;
    }

    void improveScenicScore(int value) {
       scenicScore *= value;
    }
    int getScenicScore() {
        return scenicScore;
    }
}
