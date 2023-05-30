--15. the name ( first name and last name ) for those employees who gets more salary than the employee whose ID is 163.

SELECT first_name, last_name FROM employees WHERE salary > (SELECT salary FROM employees WHERE employee_id=163)
