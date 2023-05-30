-- 2. the first and last name, department, city, and state province for each employee.

SELECT first_name, last_name, department_name, city, state_province
FROM employees LEFT JOIN departments USING(department_id)
LEFT JOIN Locations USING(location_id);