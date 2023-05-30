-- 4. those employees who contain a letter z to their first name and also display their last name, department, city, and state province.

SELECT first_name, last_name, department_name, city, state_province
FROM employees LEFT JOIN departments USING(department_id)
LEFT JOIN Locations USING(location_id) WHERE first_name LIKE '%z%';