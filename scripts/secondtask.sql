SELECT min(timestampdiff(MINUTE, v1.dateTimeStart, v2.dateTimeStart)-v1.duration) as 'inter', 
v1.title as 'Film 1', v1.dateTimeStart as 'Time start', v1.duration as 'duration',
(select title from cinema.schedule where dateTimeStart = min(v2.dateTimeStart)) as 'Film 2',
 min(v2.dateTimeStart) as 'Time start', 
 (select duration from cinema.schedule where dateTimeStart = min(v2.dateTimeStart))  as 'duration' FROM 
(select dateTimeStart, duration, title from cinema.schedule) as v1
join 
(select dateTimeStart, title, duration from cinema.schedule) as v2
where
 (timestampdiff(MINUTE, v1.dateTimeStart, v2.dateTimeStart) > v1.duration+30) and
 (strcmp(date_format(v1.dateTimeStart, "%Y %d %M"), date_format( v2.dateTimeStart, "%Y %d %M")) =0  )
 and not(hasNear(v1.dateTimeStart, v1.title, v1.duration))
 group by v1.dateTimeStart order by inter desc;