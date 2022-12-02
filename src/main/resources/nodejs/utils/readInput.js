const {readFileSync} = require('fs');
const {join} = require('path');

module.exports = function readInput(n) {
    n = +n < 10 ? '0' + n : '' + n;
    return readFileSync(
        join(__dirname,
            '..',
            '..',
            `challenge${n}-input.txt`),
        'utf-8');
}
