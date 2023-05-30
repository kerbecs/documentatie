-- 8. the details of employees who manage a department.

SELECT * FROM employees WHERE employee_id IN (SELECT DISTINCT manager_id FROM Departments)
