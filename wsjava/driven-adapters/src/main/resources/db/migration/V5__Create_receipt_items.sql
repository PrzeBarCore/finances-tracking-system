CREATE TABLE IF NOT EXISTS receipt_items (
id INTEGER PRIMARY KEY,
name TEXT,
quantity REAL ,
regular_price REAL ,
discount REAL,
receipt_id INTEGER,
product_id INTEGER,
expense_category_id INTEGER ,
FOREIGN KEY(receipt_id) REFERENCES receipts(id),
FOREIGN KEY(product_id) REFERENCES products(id),
FOREIGN KEY(expense_category_id) REFERENCES categories(id)
)

