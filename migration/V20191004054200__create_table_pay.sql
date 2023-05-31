CREATE TABLE pay
(
    cardNr         NUMBER(6) GENERATED ALWAYS AS IDENTITY (START WITH 10000 INCREMENT BY 1) PRIMARY KEY,
    salary         NUMBER(8,2),
    commission_pct NUMBER(2, 2),
    employee_id    NUMBER(6) REFERENCES employees(employee_id),
    CONSTRAINT emp_salary_min_value CHECK (salary > 0)
);


