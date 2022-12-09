const readInput = require("./utils/readInput");
const sum = require("./utils/sum");

const rucksacks = readInput(3).split("\n");
const withCompartments = rucksacks.map(getCompartments);
const wrongItems = withCompartments.map(getWrongItems);
const itemNumbers = wrongItems.map(getItemNumber);
const sum1 = itemNumbers.reduce(sum, 0);

const badgeGroups = getBadgeGroups(rucksacks);
const badgeItems = badgeGroups.map(findBadgeItem);
const badgeItemNumbers = badgeItems.map(getItemNumber);
const sum2 = badgeItemNumbers.reduce(sum, 0);

console.log([sum1, sum2]);

function getCompartments(r) {
  return [r.substring(0, r.length / 2), r.substring(r.length / 2)];
}

function getWrongItems([one, two]) {
  for (const i of one) {
    if (two.includes(i)) {
      return i;
    }
  }
}

function getItemNumber(i) {
  const ascii = i.charCodeAt(0);
  if (ascii < 97) {
    return ascii - 38;
  }
  return ascii - 96;
}

function getBadgeGroups(rucksacks) {
  return rucksacks.reduce((p, c, i) => {
    if (i % 3 === 0) {
      p.push([c]);
    } else {
      p[p.length - 1].push(c);
    }
    return p;
  }, []);
}

function findBadgeItem([r1, r2, r3]) {
  for (let i of r1) {
    if (r2.includes(i) && r3.includes(i)) {
      return i;
    }
  }
}
