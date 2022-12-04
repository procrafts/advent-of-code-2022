const readInput = require('./utils/readInput');
const sum = require('./utils/sum');

const assignments = readInput(4).split("\n");
const roomDescriptions = assignments.map(getRoomDescription);
const areFullyContained = roomDescriptions.map(isFullyContained);
const sum1 = areFullyContained.reduce(sum);

const arePartlyContained = roomDescriptions.map(isPartlyContained);
const sum2 = arePartlyContained.reduce(sum);

console.log([sum1, sum2]);

function getRoomDescription(assignment) {
    return assignment.split(",").map(s => s.split("-"));
}

function isFullyContained(roomDescription) {
    const aLeft = +roomDescription[0][0];
    const aRight = +roomDescription[0][1];
    const bLeft = +roomDescription[1][0];
    const bRight = +roomDescription[1][1];
    if (aLeft >= bLeft && aRight <= bRight) {
        return 1;
    }
    if (bLeft >= aLeft && bRight <= aRight) {
        return 1;
    }
    return 0;
}

function isPartlyContained(roomDescription) {
    const aLeft = +roomDescription[0][0];
    const aRight = +roomDescription[0][1];
    const bLeft = +roomDescription[1][0];
    const bRight = +roomDescription[1][1];
    if (bLeft > aRight || aLeft > bRight) {
        return 0;
    }
    return 1;
}