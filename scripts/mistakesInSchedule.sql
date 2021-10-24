SELECT v1.title as 'Film 1', v1.dateTimeStart as 'Time start', v1.duration as 'duration',
v2.title as 'Film 2', v2.dateTimeStart as 'Time start', v2.duration as 'duration' FROM 
(select dateTimeStart, duration, title from cinema.schedule) as v1
join 
(select dateTimeStart, title, duration from cinema.schedule) as v2
where
timestampdiff(MINUTE, v1.dateTimeStart, v2.dateTimeStart) <= v1.duration  and
timestampdiff(MINUTE,  v1.dateTimeStart, v2.dateTimeStart) > 0 
group by v1.dateTimeStart, v2.dateTimeStart order by v1.dateTimeStart ;

