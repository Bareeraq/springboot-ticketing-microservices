INSERT IGNORE INTO venue (id, name, address, total_capacity) VALUES
(1, 'Wembley Stadium', 'London, UK', 90000);

INSERT IGNORE INTO event (id, name, total_capacity, left_capacity, venue_id, ticket_price) VALUES
(4, 'Champions League Final', 90000, 90000, 1, 250.00);