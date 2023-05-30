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
SELECT employee_id,job_title,TO_DATE(CURRENT_DATE,'dd.mm.yy')-hire_date AS "days_worked"
FROM employees
JOIN jobs USING(job_id)
WHERE department_id = 80
UNION
SELECT employee_id,job_title,end_date - start_date AS "days_worked"
FROM job_history
JOIN jobs USING(job_id)
WHERE department_id = 80


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


--20. all the information of the employees whose salary is within the range of smallest salary and 2500.
SELECT *
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
      FROM (SELECT DISTINCT Salary FROM employees ORDER BY Salary DESC) OFFSET 1 ROWS FETCH NEXT 1 ROWS ONLY);

--23. the employee name( first name and last name ) and hiredate for all employees in the same department as Clara. Exclude Clara.
SELECT first_name,last_name,hire_date FROM employees
WHERE department_id=(SELECT department_id FROM employees WHERE first_name='Clara')
AND first_name!='Clara'


--TODO
--24. the employee number and name( first name and last name ) for all employees who work in a department with any employee whose name contains a T.
SELECT employee_id,first_name,last_name FROM employees
WHERE department_id IN
(SELECT DISTINCT department_id FROM employees
WHERE (LOWER(first_name) LIKE '%t%'
OR
LOWER(last_name) LIKE '%t%')
AND department_id IS NOT NULL);


--TODO
--25. full name(first and last name), job title, starting and ending date of last jobs for those employees with worked without a commission percentage.
WITH employees_max_start_date(employee_id,start_date) AS
(SELECT employee_id, MAX(start_date) AS "start_date" FROM job_history GROUP BY(employee_id)),
employees_max_start_date_and_end_date AS
(SELECT employee_id,start_date,end_date FROM job_history
JOIN employees_max_start_date USING(employee_id,start_date))
SELECT first_name,last_name,job_title,start_date,end_date FROM employees
JOIN jobs USING(job_id)
JOIN employees_max_start_date_and_end_date USING(employee_id)
WHERE commission_pct IS NULL

--TODO
--26. the employee number, name( first name and last name ), and salary for all employees who earn more than the average salary and who work in a department with any employee with a J in their name.
SELECT employee_id,first_name,last_name,salary
FROM employees
WHERE salary > (SELECT AVG(salary) FROM employees) AND department_id IN
(SELECT department_id
FROM employees
WHERE LOWER(first_name) LIKE '%j%'
OR LOWER(last_name) LIKE '%j');


--27. the employee number, name( first name and last name ) and job title for all employees whose salary is smaller than any salary of those employees whose job title is MK_MAN.
SELECT employee_id,first_name,last_name,job_title FROM employees
JOIN jobs USING(job_id)
WHERE salary < ANY(
SELECT salary FROM employees WHERE job_id='MK_MAN');

--TODO
--28. the employee number, name( first name and last name ) and job title for all employees whose salary is smaller than any salary of those employees whose job title is MK_MAN. Exclude Job title MK_MAN.
SELECT employee_id,first_name,last_name,job_title FROM employees
JOIN jobs USING(job_id)
WHERE salary < ANY(
SELECT salary FROM employees WHERE job_id='MK_MAN')
AND job_id != 'MK_MAN';

--29. all the information of those employees who did not have any job in the past.
SELECT * FROM employees WHERE employee_id NOT IN (SELECT DISTINCT employee_id FROM job_history);


--30. the employee number, name( first name and last name ) and job title for all employees whose salary is more than any average salary of any department.
WITH avg_salary_per_department(department_id,avg_salary) AS
(SELECT department_id,ROUND(AVG(salary),2) FROM employees GROUP BY(department_id))
SELECT employee_id,first_name,last_name,job_title FROM employees
JOIN jobs USING(job_id)
WHERE salary > ANY(
SELECT avg_salary FROM avg_salary_per_department)

--31. the employee id, name ( first name and last name ) and the job id column with a modified title SALESMAN for those employees whose job title is ST_MAN and DEVELOPER for whose job title is IT_PROG.
SELECT employee_id,first_name,last_name,
CASE
WHEN job_id='ST_MAN' THEN 'SALESMAN'
WHEN job_id='IT_PROG' THEN 'DEVELOPER'
ELSE job_id
END AS "job_id"
FROM employees;


--32. the employee id, name ( first name and last name ), salary and the SalaryStatus column with a title HIGH and LOW respectively for those employees whose salary is more than and less than the average salary of all employees.
WITH avg_salary(salary) AS (SELECT AVG(salary) FROM employees)
SELECT employee_id,first_name,last_name,salary,
CASE
WHEN salary < (SELECT salary FROM avg_salary) THEN 'LOW'
ELSE 'HIGH'
END AS "SalaryStatus"
FROM employees;


--33. the employee id, name ( first name and last name ), SalaryDrawn, AvgCompare (salary - the average salary of all employees)
    -- and the SalaryStatus column with a title HIGH and LOW respectively for those employees whose salary is more than and less than
    -- the average salary of all employees.
WITH avg_salary(salary) AS (SELECT ROUND(AVG(salary),2) FROM employees)
SELECT employee_id,first_name,last_name,salary AS "SalaryDrawn",
salary-(SELECT salary FROM avg_salary) AS "AvgCompare",
CASE
WHEN salary < (SELECT salary FROM avg_salary) THEN 'LOW'
ELSE 'HIGH'
END AS "SalaryStatus"
FROM employees;


--34. all the employees who earn more than the average and who work in any of the IT departments.
WITH it_departments AS
(SELECT department_id FROM departments WHERE LOWER(department_name) LIKE '%it%'),
avg_salary(salary) AS (SELECT ROUND(AVG(salary),2) FROM employees)
SELECT * FROM employees
WHERE salary > (SELECT * FROM avg_salary)
AND
department_id IN (SELECT * FROM it_departments);


--35. who earns more than Mr. Ozer.
SELECT * FROM employees
WHERE salary > (SELECT salary FROM employees WHERE last_name='Ozer')


--36. which employees have a manager who works for a department based in the US.
SELECT * FROM employees
WHERE manager_id IN
(SELECT employee_id FROM employees
WHERE employee_id IN
(SELECT DISTINCT manager_id FROM employees)
AND department_id IN
(SELECT DISTINCT department_id FROM departments
WHERE location_id IN (SELECT location_id FROM locations WHERE country_id='US')
));

--TODO
--37. the names of all employees whose salary is greater than 50% of their department’s total salary bill.
WITH departments_bill(department_id,bill) AS
(SELECT department_id,SUM(commission_pct*salary) FROM employees GROUP BY(department_id))
SELECT first_name, last_name FROM employees
WHERE salary > 0.5*(SELECT bill FROM departments_bill WHERE departments_bill.department_id=employees.department_id)

--TODO
--38. the employee id, name ( first name and last name ), salary, department name and city for all
--the employees who gets the salary as the salary earn by the employee which is maximum within the joining person January 1st, 2002 and December 31st, 2003.
SELECT employee_id,first_name,last_name,salary,department_name,city FROM employees
JOIN jobs USING(job_id)
JOIN departments USING(department_id)
JOIN locations USING(location_id)
WHERE salary = (
SELECT MAX(salary) FROM employees WHERE hire_date BETWEEN '01-JAN-02' AND '31-DEC-03');


--39. the first and last name, salary, and department ID for all those employees who earn more than the average salary and arrange the list in descending order on salary.
SELECT first_name,last_name,salary,department_id FROM employees
WHERE salary > (SELECT AVG(salary) FROM employees) ORDER BY salary DESC;


--40. the first and last name, salary, and department ID for those employees who earn more than the maximum salary of a department which ID is 40.
SELECT first_name, last_name,salary, department_id
FROM employees
WHERE salary > (SELECT "max_salary"
FROM
(SELECT department_id,MAX(salary) AS "max_salary"
FROM employees
WHERE department_id = 40
GROUP BY(department_id)));


--41. the department name and Id for all departments where they located, that Id is equal to the Id for the location where department number 30 is located.
SELECT department_id,department_name FROM departments
WHERE location_id =
(SELECT location_id FROM departments WHERE department_id = 30);


--42. the first and last name, salary, and department ID for all those employees who work in that department where the employee works who hold the ID 201.
SELECT first_name, last_name, salary, department_id FROM employees
WHERE department_id =
(SELECT department_id FROM employees WHERE employee_id=201);


--TODO
--43. the first and last name, salary, and department ID for those employees whose salary is equal to the salary of the employee who works in that department which ID is 40.
SELECT first_name, last_name, salary, department_id FROM employees
WHERE salary =
(SELECT salary FROM employees WHERE department_id = 40);

--44. the first and last name, salary, and department ID for those employees who earn more than the minimum salary of a department which ID is 40.
SELECT first_name, last_name, salary, department_id FROM employees
WHERE salary > (SELECT "min_salary" FROM
(SELECT department_id, MIN(salary) "min_salary" FROM employees
WHERE department_id=40
GROUP BY(department_id)));


--45. the first and last name, salary, and department ID for those employees who earn less than the minimum salary of a department which ID is 70.
SELECT first_name, last_name, salary, department_id FROM employees
WHERE salary > (SELECT "min_salary" FROM
(SELECT department_id, MIN(salary) "min_salary" FROM employees
WHERE department_id=70
GROUP BY(department_id)));


--46. the first and last name, salary, and department ID for those employees who earn less than the average salary, and also work at the department where the employee Laura is working as a first name holder.
WITH avg_salary("average_salary") AS (SELECT AVG(salary) FROM employees),
laura_id("id") AS (SELECT employee_id FROM employees WHERE first_name='Laura'),
laura_department_id("dep_id") AS (SELECT department_id FROM departments
WHERE manager_id = (SELECT "id" FROM laura_id))
SELECT first_name, last_name, salary, department_id FROM employees
WHERE salary < (SELECT "average_salary" FROM avg_salary)
AND department_id=(SELECT "dep_id" FROM laura_department_id)


--47. the full name (first and last name) of manager who is supervising 4 or more employees.
SELECT first_name,last_name,COUNT(employee_id) AS "employees_supervised" FROM employees
GROUP BY(manager_id) HAVING COUNT(employee_id)>=4;


--48. the details of the current job for those employees who worked as a Sales Representative in the past.
SELECT * FROM jobs
WHERE job_id =
(SELECT job_id FROM employees
WHERE employee_id IN
(SELECT employee_id FROM job_history
WHERE job_id=(SELECT job_id FROM jobs WHERE job_title='Sales Representative')));


--49. all the infromation about those employees who earn second lowest salary of all the employees.
SELECT * FROM employees WHERE salary=(
SELECT DISTINCT salary FROM employees ORDER BY salary ASC
OFFSET 1 ROWS FETCH NEXT 1 ROWS ONLY);


--50. the department ID, full name (first and last name), salary for those employees who is highest salary drawar in a department.
WITH max_salary_per_department("dep_id","max_salary")
AS (SELECT department_id,MAX(salary) FROM employees GROUP BY(department_id))
SELECT department_id,first_name,last_name,salary FROM employees
WHERE department_id IN (SELECT "dep_id" FROM max_salary_per_department)
AND salary IN (SELECT "max_salary" FROM max_salary_per_department)