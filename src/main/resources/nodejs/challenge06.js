const readInput = require('./utils/readInput');
const sum = require('./utils/sum');

const MarkerType = {Package: 4, Message: 14};

const signals = readInput(6).split("\n");
const sum1 = signals.map(getFirstMarkerFn(MarkerType.Package)).reduce(sum, 0);
const sum2 = signals.map(getFirstMarkerFn(MarkerType.Message)).reduce(sum, 0);
console.log([sum1, sum2]);

function getFirstMarkerFn(markerLength) {
    return (signal) => {
        let pointer = 0;
        const group = [];
        let marker = 0;
        while (marker === 0 && pointer < signal.length) {
            const target = signal.charAt(pointer);
            group.push(target);
            if (group.length > markerLength) {
                group.shift();
                if (isUnambiguity(group, markerLength)) {
                    marker = pointer + 1;
                }
            }
            pointer++;
        }
        return marker;
    }
}

function isUnambiguity(group, markerLength) {
    const result = new Set(group);
    return result.size === markerLength;
}