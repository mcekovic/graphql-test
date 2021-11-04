CREATE TABLE author(
    author_id IDENTITY PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE
);

CREATE TABLE book(
    book_id IDENTITY PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    author_id BIGINT NOT NULL REFERENCES author(author_id)
);