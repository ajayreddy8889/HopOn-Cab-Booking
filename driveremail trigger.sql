DELIMITER //

CREATE TRIGGER trg_check_driver_email
BEFORE INSERT ON driverinfo
FOR EACH ROW
BEGIN
    IF EXISTS (
        SELECT 1 FROM driverinfo
        WHERE LOWER(DriverEmail) = LOWER(NEW.DriverEmail)
    ) THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Email already exists for a rider.';
    END IF;
END;
//


DELIMITER ;