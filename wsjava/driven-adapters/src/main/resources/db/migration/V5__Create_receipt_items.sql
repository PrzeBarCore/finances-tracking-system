CREATE TABLE Receipt_items (
id int not null PRIMARY KEY auto_increment,
name varchar(100) NOT NULL,
quantity double NOT NULL,
regular_price double NOT NULL,
discount double,
receipt_id int,
product_id int,
category_id int, --NOT NULL,
FOREIGN KEY(receipt_id) REFERENCES receipts(id),
FOREIGN KEY(product_id) REFERENCES products(id),
FOREIGN KEY(category_id) REFERENCES categories(id)
)

