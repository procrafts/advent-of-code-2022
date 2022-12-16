const readInput = require("./utils/readInput");
const sum = require("./utils/sum");

class FileSystemNode {
  name;
  parent;

  constructor(name) {
    this.name = name;
  }

  getParent() {
    return this.parent;
  }

  setParent(parent) {
    this.parent = parent;
  }

  getSize() {
    return 0;
  }
}

class File extends FileSystemNode {
  size;

  constructor(name, size) {
    super(name);
    this.size = size;
  }

  getSize() {
    return this.size;
  }
}

class Directory extends FileSystemNode {
  children = [];

  constructor(name) {
    super(name);
  }

  getSize() {
    return this.children.map((c) => c.getSize()).reduce(sum, 0);
  }

  getChildren() {
    return this.children;
  }

  addChild(child) {
    this.children.push(child);
  }

  getChild(name) {
    const matches = this.children.filter((c) => c.name === name);
    if (matches.length) {
      return matches[0];
    }
    return null;
  }
}

function buildFileSystem(output) {
  let root = new Directory("/");
  for (const line of output) {
    if (line.startsWith("$ cd ..")) {
      root = root.getParent();
    } else if (line.startsWith("$ cd ")) {
      const name = line.substring(5);
      const parent = root;
      let child = parent.getChild(name);
      if (child == null) {
        child = new Directory(name);
        parent.addChild(child);
        child.setParent(parent);
      }
      root = child;
    } else if (line.match("\\d+ \\w+.?\\w*")) {
      const s = line.split(" ");
      const name = s[1];
      const parent = root;
      if (parent.getChild(name) == null) {
        const size = +s[0];
        const child = new File(name, size);
        child.setParent(root);
        parent.addChild(child);
      }
    }
  }

  while (root.name !== "/") {
    root = root.getParent();
  }

  return root;
}

function getTotalSize(node) {
  let sum = 0;

  if (node.getSize() <= 100000) {
    sum += node.getSize();
  }

  for (const c of node.getChildren()) {
    if (c instanceof Directory) {
      sum += getTotalSize(c);
    }
  }
  return sum;
}

function deleteSmallestDirectory(node) {
  const deletableDirectorySizes = [];
  const freeSpace = 70000000 - node.getSize();
  const spaceToFreeUp = 30000000 - freeSpace;
  findDeletableDirectory(node, spaceToFreeUp, deletableDirectorySizes);
  return deletableDirectorySizes.reduce(
    (p, c) => (p < c ? p : c),
    Number.MAX_VALUE
  );
}

function findDeletableDirectory(node, neededSize, deletableDirectorySizes) {
  const size = node.getSize();
  if (size >= neededSize) {
    deletableDirectorySizes.push(size);
  }
  for (const child of node.getChildren()) {
    if (child instanceof Directory) {
      findDeletableDirectory(child, neededSize, deletableDirectorySizes);
    }
  }
}

const output = readInput(7).split("\n");
const root = buildFileSystem(output);
const sum1 = getTotalSize(root);
const sum2 = deleteSmallestDirectory(root);
console.log([sum1, sum2]);
