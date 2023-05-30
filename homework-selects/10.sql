--10. the department name and number of employees in each of the department.

SELECT department_name, COUNT(employee_id) FROM departments
JOIN employees USING(department_id)
GROUP BY(department_name);