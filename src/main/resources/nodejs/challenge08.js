const readInput = require("./utils/readInput");

const input = readInput(8).split("\n");
const forest = Forest(input);
const sum1 = forest.countVisibleTrees();
const sum2 = forest.getTreeWithHighestScenicScore().scenicScore;
console.log([sum1, sum2]);

function Tree(height) {
  return {
    height,
    visible: false,
    scenicScore: 1,
    setVisibility(isVisible) {
      if (isVisible) {
        this.visible = true;
      }
    },
    improveScenicScore(value) {
      this.scenicScore *= value;
    },
  };
}

function Forest(input) {
  const inputArray = input.map((line) => line.split(""));
  const forest = inputArray[0].map((_, yIndex) =>
    inputArray.map((x) => x[yIndex])
  );
  for (let x = 0; x < input[0].length; x++) {
    for (let y = 0; y < input.length; y++) {
      forest[x][y] = Tree(forest[x][y]);
    }
  }
  for (let x = 0; x < input[0].length; x++) {
    for (let y = 0; y < input.length; y++) {
      setVisibility(x, y, forest[x][y]);
      improveScenicScore(x, y, forest[x][y]);
    }
  }

  function setVisibility(x, y, tree) {
    tree.setVisibility(getVisibilityVertical(x, y, 0, -1, tree));
    tree.setVisibility(
      getVisibilityVertical(x, y, forest[x].length - 1, 1, tree)
    );
    tree.setVisibility(getVisibilityHorizontal(x, y, 0, -1, tree));
    tree.setVisibility(
      getVisibilityHorizontal(x, y, forest.length - 1, 1, tree)
    );
  }

  function improveScenicScore(x, y, tree) {
    tree.improveScenicScore(getScenicScoreVertical(x, y, 0, -1, tree));
    tree.improveScenicScore(
      getScenicScoreVertical(x, y, forest[x].length - 1, 1, tree)
    );
    tree.improveScenicScore(getScenicScoreHorizontal(x, y, 0, -1, tree));
    tree.improveScenicScore(
      getScenicScoreHorizontal(x, y, forest.length - 1, 1, tree)
    );
  }

  function getVisibilityVertical(x, y, border, step, tree) {
    if (y === border) {
      return true;
    }
    let pos = y + step;
    while (pos !== border + step) {
      const otherTree = forest[x][pos];
      if (otherTree.height >= tree.height) {
        return false;
      }
      pos += step;
    }
    return true;
  }

  function getVisibilityHorizontal(x, y, border, step, tree) {
    if (x === border) {
      return true;
    }
    let pos = x + step;
    while (pos !== border + step) {
      const otherTree = forest[pos][y];
      if (otherTree.height >= tree.height) {
        return false;
      }
      pos += step;
    }
    return true;
  }

  function getScenicScoreVertical(x, y, border, step, tree) {
    let score = 0;
    if (y === border) {
      return score;
    }
    let pos = y + step;
    while (pos !== border + step) {
      const otherTree = forest[x][pos];

      score++;
      if (otherTree.height >= tree.height) {
        return score;
      }
      pos += step;
    }
    return score;
  }

  function getScenicScoreHorizontal(x, y, border, step, tree) {
    let score = 0;
    if (x === border) {
      return score;
    }
    let pos = x + step;
    while (pos !== border + step) {
      const otherTree = forest[pos][y];
      score++;
      if (otherTree.height >= tree.height) {
        return score;
      }
      pos += step;
    }
    return score;
  }

  function countVisibleTrees() {
    let count = 0;
    for (let line of forest) {
      for (let tree of line) {
        if (tree.visible) {
          count++;
        }
      }
    }
    return count;
  }

  function getTreeWithHighestScenicScore() {
    let result = null;
    for (let line of forest) {
      for (let tree of line) {
        if (result === null || tree.scenicScore > result.scenicScore) {
          result = tree;
        }
      }
    }
    return result;
  }

  return { countVisibleTrees, getTreeWithHighestScenicScore };
}
