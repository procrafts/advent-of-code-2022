const readInput = require("./utils/readInput");

const overview = readInput(5).split("\n");
const emptyStacks = getEmptyStacks(overview);
const stacksWithCrates = placeCrates(overview, emptyStacks);
const commands = getCommands(overview);
runCommands(commands, stacksWithCrates);
const sum1 = getUpperCrates(stacksWithCrates);

const emptyStacks2 = getEmptyStacks(overview);
const stacksWithCrates2 = placeCrates(overview, emptyStacks2);
runCommands2(commands, stacksWithCrates2);
const sum2 = getUpperCrates(stacksWithCrates2);

console.log([sum1, sum2]);

function getEmptyStacks(overview) {
  const stackLine = overview.find((l) => l[1] === "1");
  return stackLine
    .split(" ")
    .filter((c) => c)
    .map(() => []);
}

function placeCrates(overview, stacks) {
  for (const line of overview) {
    for (let i = 1; i < line.length; i += 4) {
      if (line[i] === "1") {
        return stacks;
      }
      const pos = i - 1 === 0 ? 0 : (i - 1) / 4;
      const crate = line[i];
      if (crate !== " ") {
        stacks[pos] = [crate, ...stacks[pos]];
      }
    }
  }
}

function getCommands(overview) {
  const commands = [];
  for (const line of overview) {
    if (line && line.startsWith("move")) {
      const commandParts = line.split(" ").filter((s) => s.match(/[0-9]+/));
      commands.push({
        amount: +commandParts[0],
        from: +commandParts[1] - 1,
        to: +commandParts[2] - 1,
      });
    }
  }
  return commands;
}

function runCommands(commands, stacks) {
  for (const command of commands) {
    for (let i = 0; i < command.amount; i++) {
      const crate = stacks[command.from].pop();
      stacks[command.to].push(crate);
    }
  }
}

function runCommands2(commands, stacks) {
  for (const command of commands) {
    const crates = [];
    for (let i = 0; i < command.amount; i++) {
      const crate = stacks[command.from].pop();
      crates.push(crate);
    }
    for (let i = 0; i < command.amount; i++) {
      const crate = crates.pop();
      stacks[command.to].push(crate);
    }
  }
}

function getUpperCrates(stacks) {
  return stacks.map((s) => s[s.length - 1]).join("");
}
