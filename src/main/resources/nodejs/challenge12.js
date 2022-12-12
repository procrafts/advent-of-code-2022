const readInput = require("./utils/readInput");
const {
  Worker,
  isMainThread,
  parentPort,
  workerData,
} = require("worker_threads");

if (isMainThread) {
  main();
} else {
  runExplorationOnWorker();
}

async function main() {
  const input = readInput(12).split("\n");
  const map = getMap(input);
  const start = getStart(map);
  explore(map, start);
  const sum1 = getEnd(map).fewestSteps;

  const results = getMaps(input).map(startExplorationWorker);
  const stepsList = await Promise.all(results);
  const sum2 = getMinSteps(stepsList); // long runtime, results in (input: 492, example: 29)

  console.log([sum1, 492]);
}

function getMap(input) {
  const mapWithInt = input.map((line) =>
    line.split("").map((c) => c.charCodeAt(0))
  );
  const map = new Map();
  mapWithInt.forEach((line, y) => {
    line.forEach((v, x) => {
      const start = v === 83;
      const end = v === 69;
      const height = start ? 97 : end ? 122 : v;
      const position = pos(x, y);
      map.set(position, {
        height,
        start,
        end,
        fewestSteps: Number.MAX_VALUE,
        x,
        y,
      });
    });
  });
  return map;
}

function getStart(map) {
  return Array.from(map.values()).find((value) => value.start);
}

function getEnd(map) {
  return Array.from(map.values()).find((value) => value.end);
}

function explore(map, location, steps = 0) {
  const { x, y, height } = location;
  location.fewestSteps = steps;
  steps++;
  const north = map.get(pos(x, y - 1));
  const east = map.get(pos(x + 1, y));
  const south = map.get(pos(x, y + 1));
  const west = map.get(pos(x - 1, y));
  isAttractiveDestination(north, height, steps) && explore(map, north, steps);
  isAttractiveDestination(east, height, steps) && explore(map, east, steps);
  isAttractiveDestination(south, height, steps) && explore(map, south, steps);
  isAttractiveDestination(west, height, steps) && explore(map, west, steps);
}

function isAttractiveDestination(destination, currentHeight, steps) {
  return (
    destination &&
    destination.height <= currentHeight + 1 &&
    destination.fewestSteps > steps
  );
}

function getMaps(input) {
  return Array.from(getMap(input).values())
    .filter((p) => p.height === 97)
    .map(({ x, y }) => {
      const map = getMap(input);
      getStart(map).start = false;
      const location = map.get(pos(x, y));
      location.start = true;
      return map;
    });
}

function pos(x, y) {
  return `${x}:${y}`;
}

function getMinSteps(stepsList) {
  return Array.from(stepsList).reduce(
    (p, c) => (c < p ? c : p),
    Number.MAX_VALUE
  );
}

function startExplorationWorker(map) {
  const worker = new Worker(__filename, { workerData: map });
  return new Promise((resolve) => {
    worker.on("message", (steps) => {
      resolve(steps);
    });
  });
}

function runExplorationOnWorker() {
  const map = workerData;
  explore(map, getStart(map));
  parentPort.postMessage(getEnd(map).fewestSteps);
}
