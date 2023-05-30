--21. all the information of the employees who does not work in those departments where some employees works whose id within the range 100 and 200.

SELECT * FROM employees WHERE department_id NOT IN (SELECT DISTINCT department_id FROM employees WHERE employee_id BETWEEN 100 AND 200)
