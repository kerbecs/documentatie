'use strict';

let secretNumber;
let score;
let highScore = '0';
let insertedNumber;
let finished = false;

resetValues();

function setSecretNumber() {
  secretNumber = Math.trunc(Math.random() * 20 + 1);
}

function resetScore() {
  score = 20;
}

function finishGame(){
    finished = true;
}

function checkHighScore() {
  if (score > highScore) {
    highScore = score;
    showHighScore();
  }
}
function showHighScore(){
    document.querySelector('.highscore').textContent = highScore;
}

function getInsertedNumber() {
  insertedNumber = Number(document.querySelector('.guess').value);
}

function resetValues() {
  setSecretNumber();
  resetScore();
  finished = false;
}

function checkInsertedNumber() {
  getInsertedNumber();

  if (!verifyIfANumberIsInserted())
    return;

  verifyIfSecretNumberIsGuessed();
}

function verifyIfANumberIsInserted() {
  if (!insertedNumber) {
    setGuessMessage('Not a number!');
    return false;
  }
  setGuessMessage('Start guessing...');
  return true;
}

function verifyIfSecretNumberIsGuessed() {
  if (insertedNumber === secretNumber)
    setGameAsWon();
  else if (insertedNumber > secretNumber)
    setGuessMessage('Too high');
  else
    setGuessMessage('Too low');

  decreaseScore();
}


function decreaseScore() {
  if (score >= 1 && !finished) {
    score--;
    document.querySelector('.score').textContent = score;
  }
  if (score === 0)
    setGameAsLost();
}

function setGuessMessage(message) {
  document.querySelector('.message').textContent = message;
}

function setGameAsLost() {
  document.querySelector('body').style.backgroundColor = 'red';
  document.querySelector('.message').textContent = 'You have lost';

  showSecretNumber();
  finishGame();
  blockCheckButton();
}

function setGameAsWon() {
  document.querySelector('body').style.backgroundColor = 'green';
  document.querySelector('.message').textContent = 'Congratulations! You have won';
  document.querySelector('.number').style.width = '25rem';

  showSecretNumber();
  finishGame();
  checkHighScore();
  blockCheckButton();
}

function showSecretNumber() {
  document.querySelector('.number').textContent = secretNumber;
}

function resetGame() {
  resetValues();
  activateCheckButton();

  document.querySelector('body').style.backgroundColor = '#222';
  document.querySelector('.message').textContent = 'Start guessing...';
  document.querySelector('.number').style.width = '15rem';
  document.querySelector('.guess').value = '';
  document.querySelector('.score').textContent = '20';
}

function blockCheckButton(){
    document.getElementById("checkButton").disabled = true;
}

function activateCheckButton(){
    document.getElementById("checkButton").disabled = false;
}

document.querySelector('.check').addEventListener('click', checkInsertedNumber);
document.querySelector('.again').addEventListener('click', resetGame);