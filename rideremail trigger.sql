DELIMITER //

CREATE TRIGGER trg_check_rider_email
BEFORE INSERT ON riderinfo
FOR EACH ROW
BEGIN
    IF EXISTS (
        SELECT 1 FROM riderinfo
        WHERE LOWER(RiderEmail) = LOWER(NEW.RiderEmail)
    ) THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Email already exists for a rider.';
    END IF;
END;
//

DELIMITER ;