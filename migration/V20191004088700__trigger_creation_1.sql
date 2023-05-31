ALTER TABLE locations ADD department_amount NUMBER DEFAULT 0 CHECK(department_amount>=0);

COMMENT ON COLUMN locations.department_amount IS
'Contains the amount of departments in the location';

CREATE TRIGGER amount_of_departments
AFTER INSERT OR DELETE
ON departments
FOR EACH ROW
BEGIN
IF deleting
THEN UPDATE locations SET department_amount = department_amount - 1
WHERE location_id = :OLD.location_id;
END IF;
IF INSERTING
THEN UPDATE locations SET department_amount = department_amount + 1
WHERE location_id = :NEW.location_id;
END IF;
END;