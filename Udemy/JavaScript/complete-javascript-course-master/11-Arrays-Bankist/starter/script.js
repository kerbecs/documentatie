'use strict';

/////////////////////////////////////////////////
/////////////////////////////////////////////////
// BANKIST APP

// Data
const account1 = {
  owner: 'Jonas Schmedtmann',
  movements: [200, 450, -400, 3000, -650, -130, 70, 1300],
  interestRate: 1.2, // %
  pin: 1111,
};

const account2 = {
  owner: 'Jessica Davis',
  movements: [5000, 3400, -150, -790, -3210, -1000, 8500, -30],
  interestRate: 1.5,
  pin: 2222,
};

const account3 = {
  owner: 'Steven Thomas Williams',
  movements: [200, -200, 340, -300, -20, 50, 400, -460],
  interestRate: 0.7,
  pin: 3333,
};

const account4 = {
  owner: 'Sarah Smith',
  movements: [430, 1000, 700, 50, 90],
  interestRate: 1,
  pin: 4444,
};

const accounts = [account1, account2, account3, account4];

// Elements
const labelWelcome = document.querySelector('.welcome');
const labelDate = document.querySelector('.date');
const labelBalance = document.querySelector('.balance__value');
const labelSumIn = document.querySelector('.summary__value--in');
const labelSumOut = document.querySelector('.summary__value--out');
const labelSumInterest = document.querySelector('.summary__value--interest');
const labelTimer = document.querySelector('.timer');

const containerApp = document.querySelector('.app');
const containerMovements = document.querySelector('.movements');

const btnLogin = document.querySelector('.login__btn');
const btnTransfer = document.querySelector('.form__btn--transfer');
const btnLoan = document.querySelector('.form__btn--loan');
const btnClose = document.querySelector('.form__btn--close');
const btnSort = document.querySelector('.btn--sort');

const inputLoginUsername = document.querySelector('.login__input--user');
const inputLoginPin = document.querySelector('.login__input--pin');
const inputTransferTo = document.querySelector('.form__input--to');
const inputTransferAmount = document.querySelector('.form__input--amount');
const inputLoanAmount = document.querySelector('.form__input--loan-amount');
const inputCloseUsername = document.querySelector('.form__input--user');
const inputClosePin = document.querySelector('.form__input--pin');

/////////////////////////////////////////////////
/////////////////////////////////////////////////
// LECTURES

const currencies = new Map([
  ['USD', 'United States dollar'],
  ['EUR', 'Euro'],
  ['GBP', 'Pound sterling'],
]);

const movements = [200, 450, -400, 3000, -650, -130, 70, 1300];

let currentAccount;
/////////////////////////////////////////////////

function loadTransactions(account){
  containerMovements.innerHTML = '';

  account.movements.forEach(addTransaction)
}

function addTransaction(amount, index){
  const type = amount > 0 ? 'deposit' : 'withdrawal';
  const html = `
          <div class="movements__row">
          <div class="movements__type movements__type--${type}">${index + 1} ${type}</div>
          <div class="movements__date">3 days ago</div>
          <div class="movements__value">${amount}$</div>
        </div>
  `
  containerMovements.insertAdjacentHTML('afterbegin', html);
}

function loadCurrentBalance(account){
  labelBalance.textContent = account.movements.reduce((sum, amount) => sum + amount)+'$';
}
function loadIncome(account){
  labelSumIn.textContent = account.movements.filter(el => el > 0).reduce((sum, el) => sum + el)+'$';
  labelSumIn.textContent = account.movements.filter(el => el > 0).reduce((sum, el) => sum + el)+'$';
}
function loadOutcome(account){
  labelSumOut.textContent = Math.abs(account.movements.filter(el => el < 0).reduce((sum, el) => sum + el))+'$';
}
function loadInterest(account){
  labelSumInterest.textContent = account.movements.reduce((sum, el) => sum + el)*0.02+'$';
}

function getUsername(fullName){
  return fullName.split(' ').map(el => el.at(0)).join('').toLowerCase();
}

function login(event){
  event.preventDefault();

  containerApp.style.opacity = '1';

  const username = inputLoginUsername.value.toLowerCase();
  const pin = Number(inputLoginPin.value);

  const account = findAccount(pin);

  if(account && getUsername(account.owner) === username && pin === account.pin) {
    currentAccount = account;
    loadAccountInfo(account)
  }
}

function findAccount(pin){
  return accounts.find((acc) => acc.pin === pin);
}
function loadAccountInfo(account){

  containerApp.style.opacity = '1.0';
  labelWelcome.textContent = `Welcome back ${account.owner}`;

  loadInterest(account);
  loadIncome(account);
  loadTransactions(account);
  loadOutcome(account);
  loadCurrentBalance(account);
}

function transferMoney(event){
  event.preventDefault();

  const accountToTransfer = findAccount(Number(inputTransferTo.value));
  const amount = Number(inputTransferAmount.value);

    currentAccount.movements.push(-amount);
    accountToTransfer.movements.push(amount);

    if(accountToTransfer
      && amount
      && accountToTransfer.pin !== currentAccount.pin
      && amount > 0
      && amount > currentAccount.movements.reduce((sum, current) => sum + current)) {
      loadAccountInfo(currentAccount);
    }
}

function closeAccount(event){
  event.preventDefault();
  const username = inputCloseUsername.value;
  const pin = Number(inputClosePin.value);

  if(username === getUsername(currentAccount.owner) && pin === currentAccount.pin){
    const index = accounts.findIndex(el => el.pin === pin);
    accounts.splice(index, 1);
    containerApp.style.opacity = '0';
  }
}

btnLogin.addEventListener('click', login)
btnTransfer.addEventListener('click',transferMoney);
btnClose.addEventListener('click', closeAccount);