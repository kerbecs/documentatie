--20. all the information of the employees whose salary is within the range of smallest salary and 2500.

SELECT * FROM employees WHERE salary BETWEEN (SELECT MIN(salary) FROM employees) AND 2500;