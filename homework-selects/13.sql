--13. the country name, city, and number of those departments where at least 2 employees are working.

SELECT department_id,country_name,city,"employees" FROM departments
JOIN locations USING(location_id)
JOIN countries USING(country_id)
JOIN (SELECT department_id,COUNT(employee_id) AS "employees" FROM employees
GROUP BY(department_id) HAVING COUNT(employee_id) >= 2)
USING (department_id);