CREATE TABLE book_entity
(
    id             BIGINT NOT NULL,
    title          VARCHAR(255),
    author         VARCHAR(255),
    isbn           VARCHAR(255),
    published_year INT,
    CONSTRAINT pk_bookentity PRIMARY KEY (id)
);

INSERT INTO book_entity (id, title, author, isbn, published_year) VALUES
(1, 'The Catcher in the Rye', 'J.D. Salinger', '9780316769488', 1951),
(2, 'To Kill a Mockingbird', 'Harper Lee', '9780061120084', 1960),
(3, '1984', 'George Orwell', '9780451524935', 1949),
(4, 'The Great Gatsby', 'F. Scott Fitzgerald', '9780743273565', 1925)
;