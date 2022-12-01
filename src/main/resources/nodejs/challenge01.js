const readInput = require('./utils/readInput');

const caloriesList = readInput(1);
const groupByElf = caloriesList.split("\n").reduce(group, [[]]);
const sumUpCaloriesOfElf = groupByElf.map(g => g.reduce(sum, 0));
const sortElvesByCaloriesDesc = sumUpCaloriesOfElf.sort(sortDesc);
const elfWithHighestCalories = sortElvesByCaloriesDesc[0];
const threeElvesWithHighestCalories = sortElvesByCaloriesDesc.slice(0, 3).reduce(sum, 0);

console.log([elfWithHighestCalories, threeElvesWithHighestCalories]);

function group(g, s) {
    if (s) {
        g[g.length - 1].push(s);
    } else {
        g.push([]);
    }
    return g
}

function sum(a, b) {
    return +a + +b;
}

function sortDesc(a, b) {
    return a < b ? 1 : -1;
}