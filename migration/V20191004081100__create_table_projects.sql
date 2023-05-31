CREATE TABLE projects
(
project_id NUMBER(6) PRIMARY KEY,
project_description varchar(100) NOT NULL CHECK(LENGTH(project_description)>10),
project_investments NUMBER(10,2) CHECK(project_investments>0)
);

CREATE TRIGGER investments
BEFORE INSERT OR UPDATE
ON projects
FOR EACH ROW
BEGIN
:new.project_investments := ROUND(:new.project_investments,-3);
END;