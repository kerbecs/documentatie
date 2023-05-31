CREATE TABLE employment_logs(
employment_log_id NUMBER(6) GENERATED ALWAYS AS IDENTITY (START WITH 1000 INCREMENT BY 1)  PRIMARY KEY,
first_name     VARCHAR2(20),
last_name      VARCHAR2(25) NOT NULL,
employment_action VARCHAR(5) NOT NULL CHECK(employment_action IN ('HIRED','FIRED')),
employment_status_updtd_tmstmp TIMESTAMP NOT NULL
);

CREATE PROCEDURE add_employee_log(first_name VARCHAR2,last_name VARCHAR2,employment_action VARCHAR)
IS
BEGIN
INSERT INTO employment_logs(first_name,last_name,employment_action,employment_status_updtd_tmstmp)
VALUES(first_name,last_name,employment_action,CURRENT_TIMESTAMP);
END;


CREATE TRIGGER add_log_for_employee
AFTER DELETE OR INSERT
ON employees
FOR EACH ROW
BEGIN
IF DELETING
THEN add_employee_log(:old.first_name,:old.last_name,'FIRED');
END IF;
IF INSERTING
THEN add_employee_log(:new.first_name,:new.last_name,'HIRED');
END IF;
END;
