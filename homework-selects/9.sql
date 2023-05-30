-- 9. the first name, last name, and department number for those employees who works in the same department as the employee who holds the last name as Taylor.

SELECT first_name, last_name, department_id FROM employees
WHERE department_id IN (SELECT department_id FROM employees WHERE last_name = 'Taylor');