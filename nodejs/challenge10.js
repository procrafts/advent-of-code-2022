const readInput = require("./utils/readInput");

const input = readInput(10).split("\n");
let x = 1;
let cycle = 0;
const functions = new Map();
functions.set("addx", addx);
functions.set("noop", noop);
let sum1 = 0;
const crt = Array.from(Array(6).keys()).map(() =>
  Array.from(Array(40).keys()).map(() => "")
);
input.forEach((line) => {
  const [methodName, value] = line.split(" ");
  const generator = functions.get(methodName)(+value);
  let breakLoop = false;
  do {
    cycle++;
    if ((cycle + 20) % 40 === 0 && cycle <= 220) {
      sum1 += x * cycle;
    }
    draw();
    breakLoop = generator.next().done;
  } while (!breakLoop);
});

const sum2 = crt.map((line) => {
  return line.join("");
});

console.log([sum1, sum2]);

function* addx(value) {
  yield;
  x += value;
}

function* noop() {}

function draw() {
  const crtPosX = (cycle % 40 || 40) - 1;
  const pointer = x - 1;
  const crtPosY = Math.floor((cycle - 1) / 40);
  if (crtPosX >= pointer && crtPosX <= pointer + 2) {
    crt[crtPosY][crtPosX] = "#";
  } else {
    crt[crtPosY][crtPosX] = ".";
  }
}
