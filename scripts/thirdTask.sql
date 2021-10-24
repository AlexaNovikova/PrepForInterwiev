 SELECT ifnull(title, 'Total') as 'Film title', sum(SaledTickets) as 'Total Amount of Saled Tickets',
 round((avg(SaledTickets)), 0) as 'Average Saled Tickets for Session',
 sum(price*SaledTickets) as 'Total sales' 
 FROM cinema.schedule group by title with rollup order by sum(price*SaledTickets);