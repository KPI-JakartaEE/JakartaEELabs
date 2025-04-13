-- Insert test authors
INSERT INTO authors (name, biography) VALUES
    ('J.K. Rowling', 'British author, best known for the Harry Potter series.'),
    ('George R.R. Martin', 'American novelist and short story writer, known for A Song of Ice and Fire.'),
    ('J.R.R. Tolkien', 'English writer, poet, philologist, and academic, author of The Lord of the Rings.');


-- Insert test books
INSERT INTO books (title, author_id, genre, description)
VALUES
    ('Harry Potter and the Philosopher''s Stone',
     (SELECT id FROM authors WHERE name = 'J.K. Rowling'),
     'Fantasy',
     'The first novel in the Harry Potter series.'),

    ('A Game of Thrones',
     (SELECT id FROM authors WHERE name = 'George R.R. Martin'),
     'Fantasy',
     'The first book of A Song of Ice and Fire series.'),

    ('The Fellowship of the Ring',
     (SELECT id FROM authors WHERE name = 'J.R.R. Tolkien'),
     'Fantasy',
     'First of three volumes of The Lord of the Rings.');


-- Insert test keywords
INSERT INTO book_keywords (book_id, keyword)
VALUES
    ((SELECT id FROM books WHERE title = 'Harry Potter and the Philosopher''s Stone'), 'magic'),
    ((SELECT id FROM books WHERE title = 'Harry Potter and the Philosopher''s Stone'), 'wizard'),
    ((SELECT id FROM books WHERE title = 'A Game of Thrones'), 'dragons'),
    ((SELECT id FROM books WHERE title = 'A Game of Thrones'), 'politics'),
    ((SELECT id FROM books WHERE title = 'The Fellowship of the Ring'), 'hobbits'),
    ((SELECT id FROM books WHERE title = 'The Fellowship of the Ring'), 'quest');