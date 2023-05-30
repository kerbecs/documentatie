-- 5. the first and last name and salary for those employees who earn less than the employee earn whose number is 182.

SELECT first_name, last_name, salary FROM employees
WHERE salary < (SELECT salary FROM Employees WHERE Employee_id=182);