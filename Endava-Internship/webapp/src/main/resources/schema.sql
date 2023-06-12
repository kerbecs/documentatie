CREATE TABLE Departments(
department_id int PRIMARY KEY AUTO_INCREMENT(1,1),
name varchar(30) NOT NULL,
location varchar(45) NOT NULL
);

CREATE TABLE Employees(
employee_id int PRIMARY KEY AUTO_INCREMENT(10000,1),
first_name varchar(40) NOT NULL,
last_name varchar(40) NOT NULL,
department_id int NOT NULL REFERENCES Departments(department_id) ON DELETE SET DEFAULT ON UPDATE CASCADE,
email varchar(30) NOT NULL UNIQUE,
phone_number long NOT NULL UNIQUE,
salary float(24) CHECK(salary>=1.0)
);