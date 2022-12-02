const readInput = require('./utils/readInput');
const sum = require('./utils/sum');

const secretList = readInput(2);
const matches = secretList.split("\n").map(l => l.split(" "));
const exactHandMatches = matches.map(m => [toElfHand(m[0]), toMyHand(m[1])]);
const recommendedPlayMatches = matches.map(m => [toElfHand(m[0]), toRecommendedPlay(m[1])]);
const exactHandScore = exactHandMatches.map(exactHand).reduce(sum, 0);
const recommendedPlayScore = recommendedPlayMatches.map(recommendedPlay).reduce(sum, 0);

console.log([exactHandScore, recommendedPlayScore]);

function toElfHand(sym) {
    return sym === "A" ? "ROCK" : sym === "B" ? "PAPER" : "SCISSORS";
}

function toMyHand(sym) {
    return sym === "X" ? "ROCK" : sym === "Y" ? "PAPER" : "SCISSORS";
}

function toRecommendedPlay(sym) {
    return sym === "X" ? "LOSE" : sym === "Y" ? "DRAW" : "WIN";
}

function getHandScore(hand) {
    return hand === "ROCK" ? 1 : hand === "PAPER" ? 2 : 3;
}

function exactHand(m) {
    const elf = m[0];
    const me = m[1];
    const handScore = getHandScore(me);
    let playScore = NaN;
    if (me === "ROCK") {
        playScore = elf === "ROCK" ? 3 : elf === "PAPER" ? 0 : 6;
    } else if (me === "PAPER") {
        playScore = elf === "ROCK" ? 6 : elf === "PAPER" ? 3 : 0;
    } else if (me === "SCISSORS") {
        playScore = elf === "ROCK" ? 0 : elf === "PAPER" ? 6 : 3;
    }
    return handScore + playScore;
}

function recommendedPlay(m) {
    const elf = m[0];
    const play = m[1];
    let handScore;
    let playScore = NaN;
    if (play === "LOSE") {
        playScore = 0
        handScore = elf === "ROCK" ? getHandScore("SCISSORS") : elf === "PAPER" ? getHandScore("ROCK") : getHandScore("PAPER");
    } else if (play === "DRAW") {
        playScore = 3;
        handScore = getHandScore(elf);
    } else if (play === "WIN") {
        playScore = 6;
        handScore = elf === "ROCK" ? getHandScore("PAPER") : elf === "PAPER" ? getHandScore("SCISSORS") : getHandScore("ROCK");
    }
    return handScore + playScore;
}
