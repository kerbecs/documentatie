'use strict';

const dice = document.querySelector(".dice");

let player1;
let player2;
let currentPlayer;
let currentScore;

resetValues();

function createPlayers(){
  player1 = new Player(0, document.getElementById("score--0"), document.getElementById("current--0"));
  player2 = new Player(1, document.getElementById("score--1"), document.getElementById("current--1"));;
}

function resetValues() {
  dice.classList.add("hidden");

  createPlayers();

  player1.scoreElement.textContent = '0';
  player2.scoreElement.textContent = '0';

  document.querySelector(".player--"+player1.id).classList.remove("player--winner");
  document.querySelector(".player--"+player2.id).classList.remove("player--winner");

  document.querySelector(".player--"+player1.id).classList.add("player--active");
  document.querySelector(".player--"+player2.id).classList.remove("player--active");

  currentPlayer = player1;
  currentScore = 0;

  document.querySelector(".btn--roll").disabled = false;
  document.querySelector(".btn--hold").disabled = false;
}

function randomValueForDice(){
  return Math.trunc(Math.random()*6+1);
}

function Player(id, scoreElement, currentScoreElement){
   this.id = id;
   this.score = 0;
   this.scoreElement = scoreElement;
   this.currentScoreElement = currentScoreElement;
}

function rotateDice(){
  if(dice.classList.contains("hidden"))
    dice.classList.remove("hidden")

  const points = randomValueForDice();

  addCurrentPointsToCurrentPlayer(points);

  dice.src = `dice-${points}.png`;

}

function addCurrentPointsToCurrentPlayer(points){
  if(points === 1) {
    currentScore = 0;
    currentPlayer.currentScoreElement.textContent = 0;
    changeCurrentPlayer();
  }
  else
    currentScore += points;
  currentPlayer.currentScoreElement.textContent = currentScore;
}

function changeCurrentPlayer(){
  document.querySelector(".player--"+currentPlayer.id).classList.remove("player--active");

  currentPlayer = currentPlayer === player1 ? player2 : player1;

  document.querySelector(".player--"+currentPlayer.id).classList.add("player--active");
}

function finishGame(){
  document.querySelector(".player--"+currentPlayer.id).classList.add("player--winner");
  document.querySelector(".btn--roll").disabled = true;
  document.querySelector(".btn--hold").disabled = true;
  document.querySelector(".dice").classList.add("hidden");
}

function holdScore(){
  currentPlayer.score += currentScore;
  currentPlayer.scoreElement.textContent = currentPlayer.score;
  currentPlayer.currentScoreElement.textContent = 0;
  currentScore = 0;

  if(currentPlayer.score >= 20) {
    finishGame();
    return ;
  }

  changeCurrentPlayer();
}

document.querySelector(".btn--roll").addEventListener("click",rotateDice);
document.querySelector(".btn--hold").addEventListener("click", holdScore);
document.querySelector(".btn--new").addEventListener("click", resetValues);

