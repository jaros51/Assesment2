CREATE TABLE book
(
    id             BIGINT NOT NULL AUTO_INCREMENT,
    title          VARCHAR(255) NOT NULL UNIQUE,
    author         VARCHAR(255) NOT NULL,
    isbn           VARCHAR(255) NOT NULL UNIQUE,
    published_year INT NOT NULL,
    CONSTRAINT pk_bookentity PRIMARY KEY (id)
);

INSERT INTO book ( title, author, isbn, published_year) VALUES
( 'The Catcher in the Rye', 'J.D. Salinger', '9780316769488', 1951),
( 'To Kill a Mockingbird', 'Harper Lee', '9780061120084', 1960),
( '1984', 'George Orwell', '9780451524935', 1949),
( 'The Great Gatsby', 'F. Scott Fitzgerald', '9780743273565', 1925),
( 'Pride and Prejudice', 'Jane Austen', '9780141040349', 1813),
( 'The Lord of the Rings', 'J.R.R. Tolkien', '9780261102385', 1954),
( 'The Hobbit', 'J.R.R. Tolkien', '9780261102217', 1937),
( 'Fahrenheit 451', 'Ray Bradbury', '9781451673319', 1953),
( 'Brave New World', 'Aldous Huxley', '9780060850524', 1932);

CREATE TABLE book_copy
(
    id         BIGINT NOT NULL AUTO_INCREMENT,
    book_id    BIGINT NOT NULL,
    available  BOOLEAN NOT NULL,
    CONSTRAINT pk_bookcopy PRIMARY KEY (id),
    CONSTRAINT fk_bookcopy_book FOREIGN KEY (book_id) REFERENCES book (id)
);

INSERT INTO book_copy (book_id, available) VALUES
(1, 1),
(1, 1),
(1, 0),
(1, 0),
(2, 1),
(3, 1),
(4, 1),
(5, 1),
(6, 0),
(7, 0),
(8, 0),
(9, 0);