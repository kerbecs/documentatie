-- 7. the first name of all employees and the first name of their manager including those who does not working under any manager.

SELECT E1.first_name,E2.first_name AS "Manager_First_name" FROM employees E1
LEFT JOIN employees E2 ON E1.Manager_id = E2.Employee_ID;