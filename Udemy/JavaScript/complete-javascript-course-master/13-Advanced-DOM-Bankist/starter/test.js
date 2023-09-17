function Student(name){
  this.name = name;
}

const student1 = new Student('test1');

Student.prototype = {test: 'test'};

const student2 = new Student('test2');


console.log(student1.test);   //test
console.log(student2.test);   //undefined
console.log(student1.__proto__ === student2.__proto__) //false


