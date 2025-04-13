-- Drop tables before creation
DROP TABLE IF EXISTS book_keywords;
DROP TABLE IF EXISTS books;
DROP TABLE IF EXISTS authors;

-- Table creation
CREATE TABLE authors
(
    id        UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    name      VARCHAR(255) NOT NULL UNIQUE,
    biography TEXT
);

CREATE TABLE books
(
    id          UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    title       VARCHAR(255) NOT NULL,
    author_id   UUID         NOT NULL,
    genre       VARCHAR(255),
    description TEXT,
    FOREIGN KEY (author_id) REFERENCES authors (id) ON DELETE CASCADE
);

CREATE TABLE book_keywords
(
    book_id UUID NOT NULL,
    keyword VARCHAR(255) NOT NULL,
    PRIMARY KEY (book_id, keyword),
    FOREIGN KEY (book_id) REFERENCES books (id) ON DELETE CASCADE
);

