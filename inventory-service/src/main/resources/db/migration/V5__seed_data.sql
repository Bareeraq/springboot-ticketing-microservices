INSERT INTO venue (name, address, total_capacity) VALUES
('Madison Square Garden', '4 Pennsylvania Plaza, New York, NY', 20000),
('The O2 Arena', 'Peninsula Square, London, UK', 20000),
('Hollywood Bowl', '2301 N Highland Ave, Los Angeles, CA', 17500);

INSERT INTO event (id, name, total_capacity, left_capacity, venue_id, ticket_price) VALUES
(1, 'Rock Legends Live', 20000, 15000, (SELECT id FROM venue WHERE name = 'Madison Square Garden'), 89.99),
(2, 'Symphony Under the Stars', 17500, 12000, (SELECT id FROM venue WHERE name = 'Hollywood Bowl'), 65.50),
(3, 'World Tour Finale', 20000, 5000, (SELECT id FROM venue WHERE name = 'The O2 Arena'), 120.00);