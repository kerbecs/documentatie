--22. all the information for those employees whose id is any id who earn the second highest salary.

SELECT * FROM employees WHERE salary IN
(SELECT Salary  FROM (SELECT DISTINCT Salary FROM employees ORDER BY Salary DESC) OFFSET 2 ROWS FETCH NEXT 1 ROWS ONLY);