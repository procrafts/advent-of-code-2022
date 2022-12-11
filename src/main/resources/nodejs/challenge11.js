const readInput = require("./utils/readInput");

const input = readInput(11, false).split("\n");
const sum1 = play(20, true);
const sum2 = play(10000, false);
console.log([sum1, sum2]);

function play(rounds, worryAboutItems) {
  const monkeys = getMonkey(input);
  const divisor = getDivisor(monkeys);
  for (let i = 1; i <= rounds; i++) {
    monkeys.forEach((monkey) => {
      monkey.items.forEach((level) => {
        level = increaseWorryLevel(
          level,
          monkey.operator,
          monkey.operand,
          divisor
        );
        monkey.inspected++;
        level = worryAboutItems ? reduceWorryLevel(level) : level;
        const target = getTargetMonkey(
          level,
          monkey.divisibleBy,
          monkey.ifTrue,
          monkey.ifFalse
        );
        monkeys[target].items.push(level);
      });
      monkey.items = [];
    });
  }
  return getSum(monkeys);
}

function getMonkey(input) {
  const monkeys = [];
  let line = 0;
  while (line <= input.length) {
    const items = input[line + 1]
      .match(/^\s{2}Starting items: (.+)$/)[1]
      .split(", ")
      .map((item) => +item);
    const operator = input[line + 2].match(/([*+])/)[1];
    const operand = input[line + 2].match(/(\d+|old)$/)[1];
    const divisibleBy = input[line + 3].match(/(\d+)$/)[1];
    const ifTrue = input[line + 4].match(/(\d+)$/)[1];
    const ifFalse = input[line + 5].match(/(\d+)$/)[1];
    monkeys.push({
      items,
      operator,
      operand,
      divisibleBy,
      ifTrue,
      ifFalse,
      inspected: 0,
    });
    line += 7;
  }
  return monkeys;
}

function reduceWorryLevel(level) {
  return Math.floor(level / 3);
}

function increaseWorryLevel(level, operator, operand, divisor) {
  const o = operand === "old" ? level : +operand;
  return operator === "*" ? (level * o) % divisor : (level + o) % divisor;
}

function getTargetMonkey(level, divisibleBy, ifTrue, ifFalse) {
  return level % divisibleBy ? ifFalse : ifTrue;
}

function getDivisor(monkeys) {
  return monkeys
    .map((m) => m.divisibleBy)
    .reduce((p, c) => (p % c ? (c % p ? c * p : c) : p), 1);
}

function getSum(monkeys) {
  const sorted = [...monkeys].sort((a, b) =>
    a.inspected < b.inspected ? 1 : -1
  );
  return sorted[0].inspected * sorted[1].inspected;
}
