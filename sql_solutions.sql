--1. the first name, last name, salary, and job grade for all employees.
SELECT first_name, last_name, salary, job_title
FROM employees
         LEFT JOIN jobs USING (job_id);


-- 2. the first and last name, department, city, and state province for each employee.
SELECT first_name, last_name, department_name, city, state_province
FROM employees
         LEFT JOIN departments USING (department_id)
         LEFT JOIN Locations USING (location_id);


-- 3. the first name, last name, department number and department name, for all employees for departments 80 or 40.
SELECT first_name, last_name, department_id, department_name
FROM employees
         LEFT JOIN departments USING (department_id)
WHERE department_id BETWEEN 40 AND 80;


-- 4. those employees who contain a letter z to their first name and also display their last name, department, city, and state province.
SELECT first_name, last_name, department_name, city, state_province
FROM employees
         LEFT JOIN departments USING (department_id)
         LEFT JOIN Locations USING (location_id)
WHERE first_name LIKE '%z%';


-- 5. the first and last name and salary for those employees who earn less than the employee earn whose number is 182.
SELECT first_name, last_name, salary
FROM employees
WHERE salary < (SELECT salary FROM Employees WHERE Employee_id = 182);


-- 6. the first name of all employees including the first name of their manager.
SELECT E1.first_name, E2.first_name AS "Manager_First_name"
FROM employees E1
         JOIN employees E2 ON E1.Manager_id = E2.Employee_ID;


-- 7. the first name of all employees and the first name of their manager including those who does not working under any manager.
SELECT E1.first_name, E2.first_name AS "Manager_First_name"
FROM employees E1
         LEFT JOIN employees E2 ON E1.Manager_id = E2.Employee_ID;


-- 8. the details of employees who manage a department.
SELECT *
FROM employees
WHERE employee_id IN (SELECT DISTINCT manager_id FROM Departments)


-- 9. the first name, last name, and department number for those employees who works in the same department as the employee who holds the last name as Taylor.
SELECT first_name, last_name, department_id
FROM employees
WHERE department_id IN (SELECT department_id FROM employees WHERE last_name = 'Taylor');


--10. the department name and number of employees in each of the department.
SELECT department_name, COUNT(employee_id)
FROM departments
         JOIN employees USING (department_id)
GROUP BY(department_name);


--11. the name of the department, average salary and number of employees working in that department who got commission.
WITH Tabel1(department_id, department_name, employees) AS (SELECT department_id,
                                                                  department_name,
                                                                  COUNT(CASE WHEN commission_pct IS NOT NULL THEN 1 ELSE NULL END) AS "employees"
                                                           FROM employees
                                                                    JOIN departments USING (department_id)
                                                           GROUP BY(department_id, department_name)),
     Tabel2(department_id, department_name, average_salary) AS (SELECT department_id,
                                                                       department_name,
                                                                       ROUND(AVG(salary), 2) AS "average_salary"
                                                                FROM employees E1
                                                                         JOIN departments USING (department_id)
                                                                GROUP BY(department_id, department_name))
SELECT Tabel1.department_name, employees, average_salary
FROM Tabel1
         JOIN Tabel2 USING (department_id);


--12. job title and average salary of employees.
SELECT job_id, "Average_salary"
FROM jobs
         JOIN
     (SELECT job_id, AVG(salary) AS "Average_salary"
      FROM employees
      GROUP BY(job_id)) USING (job_id);


--13. the country name, city, and number of those departments where at least 2 employees are working.
SELECT department_id, country_name, city, "employees"
FROM departments
         JOIN locations USING (location_id)
         JOIN countries USING (country_id)
         JOIN (SELECT department_id, COUNT(employee_id) AS "employees"
               FROM employees
               GROUP BY(department_id)
               HAVING COUNT(employee_id) >= 2)
              USING (department_id);


--14. the employee ID, job name, number of days worked in for all those jobs in department 80.
SELECT first_name, last_name
FROM employees
WHERE salary > (SELECT salary FROM employees WHERE employee_id = 163)


--15. the name ( first name and last name ) for those employees who gets more salary than the employee whose ID is 163.
SELECT first_name, last_name
FROM employees
WHERE salary > (SELECT salary FROM employees WHERE employee_id = 163)


--16. the employee id, employee name (first name and last name ) for all employees who earn more than the average salary.
SELECT employee_id, first_name, last_name
FROM employees
WHERE salary > (Select AVG(salary) FROM employees)


--17. the employee name ( first name and last name ), employee id and salary of all employees who report to Payam.
SELECT employee_id, first_name, last_name, salary
FROM employees
WHERE manager_id = (SELECT employee_id FROM employees WHERE first_name = 'Payam')


--18. the department number, name ( first name and last name ), job and department name for all employees in the Finance department.
SELECT department_id, department_name, first_name, last_name, job_title
FROM employees
         JOIN jobs USING (job_id)
         JOIN departments USING (department_id)
WHERE department_id = (SELECT department_id FROM departments WHERE department_name = 'Finance')


--19. all the information of an employee whose id is any of the number 134, 159 and 183.
SELECT *
FROM employees
WHERE employee_id IN (134, 159, 183) FETCH NEXT 1 ROWS ONLY;


--20. all the information of the employees whose salary is within the range of smallest salary and 2500.SELECT *
FROM employees
WHERE salary BETWEEN (SELECT MIN(salary) FROM employees) AND 2500;
    

--21. all the information of the employees who does not work in those departments where some employees works whose id within the range 100 and 200.SELECT *
FROM employees
WHERE department_id NOT IN (SELECT DISTINCT department_id FROM employees WHERE employee_id BETWEEN 100 AND 200)


--22. all the information for those employees whose id is any id who earn the second highest salary.
SELECT *
FROM employees
WHERE salary IN
      (SELECT Salary
       FROM (SELECT DISTINCT Salary FROM employees ORDER BY Salary DESC) OFFSET 2 ROWS FETCH NEXT 1 ROWS ONLY);