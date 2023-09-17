'use strict';

const hiddenPage = document.querySelector(".modal");
const blurPage = document.querySelector(".overlay");
const buttons = document.querySelectorAll(".show-modal");
const closeButton = document.querySelector(".close-modal");

closeButton.addEventListener('click',hidePage);
blurPage.addEventListener('click', hidePage);

document.addEventListener("keydown",function(event) {
  if(event.key==='Escape')
    hidePage();
})

for(let button of buttons)
  button.addEventListener('click',showPage);

function showPage(){
  blurPage.classList.remove("hidden");
  hiddenPage.classList.remove("hidden");
}

function hidePage(){
  blurPage.classList.add("hidden");
  hiddenPage.classList.add("hidden");
}

