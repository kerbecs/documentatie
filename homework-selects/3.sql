-- 3. the first name, last name, department number and department name, for all employees for departments 80 or 40.


SELECT first_name, last_name, department_id, department_name FROM employees
LEFT JOIN departments USING(department_id) WHERE department_id BETWEEN 40 AND 80;