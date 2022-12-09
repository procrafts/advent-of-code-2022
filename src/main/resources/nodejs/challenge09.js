const readInput = require("./utils/readInput");

const input = readInput(9).split("\n");
const moves = getMoves(input);
const rope = [getHead()];
for (let i = 0; i < 9; i++) {
  rope.push(getTail(rope[i]));
}

moves.forEach((m) => {
  const { direction, steps } = m;
  for (let i = 0; i < steps; i++) {
    rope.forEach((knot, i) => {
      i === 0 ? knot.makeMove(direction) : knot.react();
    });
  }
});

const sum1 = rope[1].visited.size;
const sum2 = rope[rope.length - 1].visited.size;

console.log([sum1, sum2]);

function getMoves(input) {
  return input.map((m) => {
    const [direction, steps] = m.split(" ");
    return { direction, steps };
  });
}

function getHead() {
  const pos = { x: 0, y: 0 };

  function makeMove(direction) {
    switch (direction) {
      case "U":
        return pos.y++;
      case "R":
        return pos.x++;
      case "D":
        return pos.y--;
      case "L":
        return pos.x--;
    }
  }

  return {
    makeMove,
    pos,
  };
}

function getTail(head) {
  let pos = { ...head.pos };
  const visited = new Set();

  function react() {
    const { x, y } = getReactionMove();
    pos.x = x;
    pos.y = y;
    addVisited();
  }

  function getReactionMove() {
    const { x: hx, y: hy } = head.pos;
    const { x, y } = pos;
    if (hx === x && hy === y + 2) {
      return { x, y: y + 1 };
    } else if (hx === x + 1 && hy === y + 2) {
      return { x: x + 1, y: y + 1 };
    } else if (hx === x + 2 && hy === y + 2) {
      return { x: x + 1, y: y + 1 };
    } else if (hx === x + 2 && hy === y + 1) {
      return { x: x + 1, y: y + 1 };
    } else if (hx === x + 2 && hy === y) {
      return { x: x + 1, y: y };
    } else if (hx === x + 2 && hy === y - 1) {
      return { x: x + 1, y: y - 1 };
    } else if (hx === x + 2 && hy === y - 2) {
      return { x: x + 1, y: y - 1 };
    } else if (hx === x + 1 && hy === y - 2) {
      return { x: x + 1, y: y - 1 };
    } else if (hx === x && hy === y - 2) {
      return { x, y: y - 1 };
    } else if (hx === x - 1 && hy === y - 2) {
      return { x: x - 1, y: y - 1 };
    } else if (hx === x - 2 && hy === y - 2) {
      return { x: x - 1, y: y - 1 };
    } else if (hx === x - 2 && hy === y - 1) {
      return { x: x - 1, y: y - 1 };
    } else if (hx === x - 2 && hy === y) {
      return { x: x - 1, y };
    } else if (hx === x - 2 && hy === y + 1) {
      return { x: x - 1, y: y + 1 };
    } else if (hx === x - 2 && hy === y + 2) {
      return { x: x - 1, y: y + 1 };
    } else if (hx === x - 1 && hy === y + 2) {
      return { x: x - 1, y: y + 1 };
    } else {
      return pos;
    }
  }

  function addVisited() {
    visited.add(`${pos.x},${pos.y}`);
  }

  return { visited, react, pos };
}
