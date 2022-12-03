const {readFileSync} = require('fs');
const {join} = require('path');

module.exports = function readInput(n, asExample = false) {
    const type = asExample ? 'example' : 'input';
    n = +n < 10 ? '0' + n : '' + n;
    return readFileSync(
        join(__dirname,
            '..',
            '..',
            `challenge${n}-${type}.txt`),
        'utf-8');
}
