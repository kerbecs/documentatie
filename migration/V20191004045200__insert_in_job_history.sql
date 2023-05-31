INSERT INTO regions
VALUES (1, 'America');

INSERT INTO countries
VALUES ('US', 'United States of America', 1);

INSERT INTO locations
VALUES (1000, 'Mr John Smith. 132', '2092', 'Dallas', 'Texas', 'US');

INSERT INTO jobs
VALUES ('SA_MAN', 'Sales Manager', 4200, 10000);
INSERT INTO jobs
VALUES ('ACC', 'Accounter', 4000, 6000);
INSERT INTO jobs
VALUES ('IT_PROGR', 'Programmer', 4000, 9000);

INSERT INTO departments
VALUES (10, 'IT', NULL, 1000);
INSERT INTO departments
VALUES (20, 'Marketing', NULL, 1000);


INSERT INTO employees
VALUES (100, 'Jack', 'Mayor', 'jack.maior@gmail.com', '650.256.8963', '20-JUN-18', 'SA_MAN', 8000, NULL, NULL, 20);
UPDATE departments
SET manager_id=100
WHERE department_name = 'Marketing';

INSERT INTO employees
VALUES (200, 'Mike', 'Detroit', 'mike.detroit@gmail.com', '650.289.2533', '22-DEC-22', 'IT_PROGR', 5000, NULL, 200, 10);

INSERT INTO job_history
VALUES (200, '15-AUG-18', '21-DEC-22', 'ACC', 20);