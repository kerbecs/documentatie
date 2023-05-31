ALTER TABLE employees
    ADD cardNr NUMBER(6) REFERENCES pay(cardNr);

INSERT INTO pay(employee_id, salary, commission_pct)
SELECT employee_id, salary, commission_pct
FROM employees;

MERGE INTO employees USING pay
    ON (pay.employee_id = employees.employee_id)
    WHEN MATCHED THEN
        UPDATE SET employees.cardNr = pay.cardNr;

