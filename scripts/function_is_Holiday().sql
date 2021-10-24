DELIMITER $$
CREATE DEFINER=`root`@`localhost` FUNCTION `is_Holiday`(d datetime) RETURNS tinyint(1)
    READS SQL DATA
BEGIN
 return  
       (
       select count(*) 
         from 
            holidays
        where 
        holidays.dateOfHoliday = date_format(d, '%Y-%m-%d')
       );
return 0;
END$$
DELIMITER ;
