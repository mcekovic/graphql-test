INSERT INTO author (name)
VALUES
('Martin Fowler'),
('Eric Evans'),
('Robert C. Martin'),
('Dave Farlay'),
('Martin Kleppmann');

INSERT INTO book (title, author_id)
VALUES
('Refactoring', SELECT author_id FROM author WHERE name = 'Martin Fowler'),
('Patterns of Enterprise Application Architecture', SELECT author_id FROM author WHERE name = 'Martin Fowler'),
('Domain Driven Design', SELECT author_id FROM author WHERE name = 'Eric Evans'),
('Clean Code', SELECT author_id FROM author WHERE name = 'Robert C. Martin'),
('Clean Coder', SELECT author_id FROM author WHERE name = 'Robert C. Martin'),
('Clean Architecture', SELECT author_id FROM author WHERE name = 'Robert C. Martin'),
('Continuous Delivery', SELECT author_id FROM author WHERE name = 'Dave Farlay'),
('Designing Data-Intensive Applications', SELECT author_id FROM author WHERE name = 'Martin Kleppmann');