-- Drop tables if they exist
DROP TABLE IF EXISTS books;
DROP TABLE IF EXISTS authors;

-- Authors table
CREATE TABLE authors (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name TEXT,
    age INT
);

-- Books table
CREATE TABLE books (
    isbn VARCHAR(50) NOT NULL,
    title TEXT,
    author_id BIGINT,
    PRIMARY KEY (isbn),
    CONSTRAINT fk_author FOREIGN KEY (author_id) REFERENCES authors(id)
);
