DELIMITER $$
CREATE DEFINER=`root`@`localhost` FUNCTION `hasNear`(d datetime, n varchar(45), duration int) RETURNS tinyint(1)
    READS SQL DATA
BEGIN
return (select count(*)
		from 
		schedule
        where 
		timestampdiff(MINUTE, d, schedule.dateTimeStart) < duration+30
	    and
        timestampdiff(MINUTE, d, schedule.dateTimeStart) > 0);
RETURN 0;
END$$
DELIMITER ;
