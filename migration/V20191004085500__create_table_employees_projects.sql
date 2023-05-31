CREATE TABLE employees_projects(
employee_id NUMBER(6),
project_id NUMBER (6),
hours NUMBER(6,2) DEFAULT 0 CHECK(hours>0),
CONSTRAINT employee_id_fk FOREIGN KEY(employee_id) REFERENCES employees(employee_id),
CONSTRAINT project_id_fl FOREIGN KEY(project_id) REFERENCES projects(project_id),
CONSTRAINT employees_project_pk PRIMARY KEY(employee_id,project_id)
);