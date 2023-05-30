--18. the department number, name ( first name and last name ), job and department name for all employees in the Finance department.

SELECT department_id, department_name, first_name, last_name, job_title FROM employees
JOIN jobs USING(job_id)
JOIN departments USING(department_id)
WHERE department_id=(SELECT department_id FROM departments WHERE department_name='Finance')