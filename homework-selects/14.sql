--14. the employee ID, job name, number of days worked in for all those jobs in department 80.

SELECT first_name, last_name FROM employees WHERE salary > (SELECT salary FROM employees WHERE employee_id=163)
