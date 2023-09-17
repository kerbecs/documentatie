let test;
const func = function() {
  let counter = 0;
    test = function() {
    counter++;
    console.log(counter);
  }
}

func()

test()
test()
test()

console.dir(test)