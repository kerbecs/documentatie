--19. all the information of an employee whose id is any of the number 134, 159 and 183.

SELECT * FROM employees WHERE employee_id IN(134,159,183) FETCH NEXT 1 ROWS ONLY;