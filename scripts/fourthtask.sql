SELECT  timeintervalBegin, timeintervalEnd, if(is_Holiday(dateTimeStart), 'is Holiday', 'Weekday') as 'Holiday',
 sum(SaledTickets) as 'Saled Tickes for interval', sum(saledTickets*price) as 'Total sales'
FROM cinema.schedule group by timeintervalId;