--1. the first name, last name, salary, and job grade for all employees.

SELECT first_name, last_name, salary, job_title
FROM employees
         LEFT JOIN jobs USING (job_id);