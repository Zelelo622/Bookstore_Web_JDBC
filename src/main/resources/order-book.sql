CREATE TABLE order_book (
    orderb_id INTEGER NOT NULL,
    book_id INTEGER NOT NULL,
    quantity INTEGER NOT NULL,
    PRIMARY KEY (orderb_id, book_id),
    FOREIGN KEY (orderb_id) REFERENCES orderb(orderb_id),
    FOREIGN KEY (book_id) REFERENCES book(book_id)
);