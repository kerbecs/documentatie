--17. the employee name ( first name and last name ), employee id and salary of all employees who report to Payam.

SELECT employee_id, first_name, last_name, salary FROM employees
WHERE manager_id = (SELECT employee_id FROM employees WHERE first_name='Payam')