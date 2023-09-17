'use strict';

///////////////////////////////////////
// Modal window

const modal = document.querySelector('.modal');
const overlay = document.querySelector('.overlay');
const btnCloseModal = document.querySelector('.btn--close-modal');
const btnsOpenModal = document.querySelectorAll('.btn--show-modal');
const tabContainer = document.querySelector('.operations__tab-container');
const nav = document.querySelector(".nav");
const header = document.querySelector(".header");
const dotsContainer = document.querySelector('.dots');



const openModal = function () {
  modal.classList.remove('hidden');
  overlay.classList.remove('hidden');
};

const closeModal = function () {
  modal.classList.add('hidden');
  overlay.classList.add('hidden');
};

for (let i = 0; i < btnsOpenModal.length; i++)
  btnsOpenModal[i].addEventListener('click', openModal);

btnCloseModal.addEventListener('click', closeModal);
overlay.addEventListener('click', closeModal);

document.addEventListener('keydown', function (e) {
  if (e.key === 'Escape' && !modal.classList.contains('hidden')) {
    closeModal();
  }
});

function scroll(e, elementToScroll){
  e.preventDefault();

  const element = document.querySelector(`${elementToScroll}`)
  const coord = element.getBoundingClientRect();

  window.scrollTo(coord.x + window.scrollX, coord.y + window.scrollY);
}


document.querySelector(".nav__links").addEventListener('click', (e) => {
  const target = e.target;
  const hrefId = target.getAttribute('href');
  e.preventDefault();

  if(!target.classList.contains('nav__links')){
    document.querySelector(hrefId).scrollIntoView();
  }
})

tabContainer.addEventListener('click',(e) =>{
  const tab = e.target.closest('.operations__tab');
  const tabs = e.currentTarget.querySelectorAll('.operations__tab');

  if(tab === null) return;

  const slides = document.querySelectorAll('.operations__content');
  const slide = document.querySelector(`.operations__content--${tab.dataset.tab}`);

  tabs.forEach(t => t.classList.remove('operations__tab--active'));
  slides.forEach(s => s.classList.remove('operations__content--active'));

  tab.classList.add('operations__tab--active');
  slide.classList.add('operations__content--active');
});

function changeButtonOpacity(e, value){
  const current = e.target.closest('.nav__item');

  if(!current)
    return ;

  const buttons = e.currentTarget.querySelectorAll('.nav__item');
  buttons.forEach(b => {if(b !== current) b.style.opacity = String(value)});
}

nav.addEventListener('mouseover',(e) => changeButtonOpacity(e,0.5));
nav.addEventListener('mouseout', (e) => changeButtonOpacity(e,1.0));

const navObserverOptions = {
  root: null,
  threshold: 0,
  rootMargin: -nav.getBoundingClientRect().height+'px'
}
const navObserver = new IntersectionObserver((entries) => {
  entries.forEach(e => e.isIntersecting ? nav.classList.remove('sticky') : nav.classList.add('sticky'))
})

navObserver.observe(header)

const sectionObserverOptions = {
  root: null,
  threshold: 0.15
}

const sectionObserver = new IntersectionObserver((elements) => {
  elements.forEach((e) => {
    if(e.isIntersecting) {
      e.target.classList.remove('section--hidden');
      sectionObserver.unobserve(e.target);
    }
  })
})

document.querySelectorAll('.section').forEach(e => {
  e.classList.add('section--hidden');
  sectionObserver.observe(e)
});

const imgObserverConfig = {
  root: null,
  threshold: 0,
  rootMargin: '100px'
}
const imgObserver = new IntersectionObserver((entries) => {
  entries.forEach(e => {
    if(e.isIntersecting) {
      e.target.src = e.target.dataset.src;
      e.target.addEventListener('load',(el) => el.target.classList.remove('lazy-img'));
      imgObserver.unobserve(e.target);
    }
  }, imgObserverConfig)
})

document.querySelectorAll(".lazy-img").forEach((img) => {
  imgObserver.observe(img);
})

let currentSlide = 0;

const slideButtonLeft = document.querySelector('.slider__btn--left');
const slideButtonRight = document.querySelector('.slider__btn--right')
const slides = new Array(...document.querySelectorAll('.slide'));

slides.forEach((e, index) => {
  e.style.transform = `translateX(${100*index}%)`;
})

function changeSlide(){
  slides.forEach((slide, index) => {
    let size = (index - currentSlide)*100;
    slide.style.transform = `translate(${size}%)`;
  })
  changeButton();
}
function changeSlideRight(e) {
  if (currentSlide === slides.length - 1)
    currentSlide = 0;
  else
    currentSlide++;

  changeSlide();
}
function changeSlideLeft(e){
  if (currentSlide === 0)
    currentSlide = slides.length-1;
  else
    currentSlide--;

  changeSlide();
}
slideButtonRight.addEventListener('click', changeSlideRight);
slideButtonLeft.addEventListener('click', changeSlideLeft);

function addButtons(){
  slides.forEach((s, i) => dotsContainer.insertAdjacentHTML('beforeend',
  `<button class='dots__dot' data-slide='${i}'> </button>`));

  document.querySelector('.dots__dot').classList.add('dots__dot--active');
}

addButtons();

function changeButton(){
  const dots = document.querySelectorAll('.dots__dot');
  dots.forEach(e => e.classList.remove('dots__dot--active'));
  dots[currentSlide].classList.add('dots__dot--active');

}

dotsContainer.addEventListener('click',function(e) {
  const target = e.target;

  if(e.target.classList.contains('dots__dot')){
    currentSlide = Number(target.dataset.slide);

    changeSlide();
    changeButton();
  }
})


