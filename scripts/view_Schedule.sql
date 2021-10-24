CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `schedule` 
AS select `films`.`id` AS `id`,
`films`.`title` AS `title`,
`films`.`duration` AS `duration`,
`sessions`.`dateTimeStart` AS `dateTimeStart`,
`prices`.`price` AS `price`,
`timeintervals`.`begin` AS `timeintervalBegin`,
`timeintervals`.`end` AS `timeintervalEnd`,
`timeintervals`.`id` AS `timeintervalId`,
(select count(0) from `tickets` where (`sessions`.`id` = `tickets`.`sessionId`)) AS `SaledTickets` 
 from (((`sessions` join `films` on((`sessions`.`filmId` = `films`.`id`))) 
 join `prices` on((`sessions`.`filmId` = `prices`.`filmId`))) 
 join `timeintervals` on((`prices`.`timeIntervalId` = `timeintervals`.`id`)))
 where ((date_format(`sessions`.`dateTimeStart`,'%H:%i:%s') between `timeintervals`.`begin` and `timeintervals`.`end`) 
 and (`timeintervals`.`isHoliday` = `IS_HOLIDAY`(`sessions`.`dateTimeStart`))) order by `sessions`.`dateTimeStart`;
