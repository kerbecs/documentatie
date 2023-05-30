--16. the employee id, employee name (first name and last name ) for all employees who earn more than the average salary.

SELECT employee_id,first_name, last_name FROM employees WHERE salary > (Select AVG(salary) FROM employees)
