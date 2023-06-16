CREATE TABLE Receipt_items (
id int not null PRIMARY KEY auto_increment,
name varchar(100) NOT NULL,
quantity double NOT NULL,
regularPrice double NOT NULL,
discount double,
receipt_id int NOT NULL,
product_id int,
product_category_id int NOT NULL,
FOREIGN KEY(receipt_id) REFERENCES receipts(id),
FOREIGN KEY(product_id) REFERENCES products(id),
FOREIGN KEY(product_category_id) REFERENCES transaction_categories(id)
)

