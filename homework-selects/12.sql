--12. job title and average salary of employees.

SELECT job_id,"Average_salary" FROM jobs
JOIN
(SELECT job_id,AVG(salary) AS "Average_salary" FROM employees
GROUP BY(job_id)) USING(job_id);