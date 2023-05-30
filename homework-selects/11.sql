--11. the name of the department, average salary and number of employees working in that department who got commission.

WITH Tabel1(department_id, department_name, employees) AS (SELECT department_id, department_name, COUNT(CASE WHEN commission_pct IS NOT NULL THEN 1 ELSE NULL END) AS "employees" FROM employees
JOIN departments USING(department_id) GROUP BY(department_id, department_name)),
Tabel2(department_id, department_name, average_salary) AS (SELECT department_id, department_name, ROUND(AVG(salary),2) AS "average_salary" FROM employees E1
JOIN departments USING(department_id) GROUP BY(department_id, department_name))
SELECT Tabel1.department_name,employees,average_salary FROM Tabel1
 JOIN Tabel2 USING(department_id);
