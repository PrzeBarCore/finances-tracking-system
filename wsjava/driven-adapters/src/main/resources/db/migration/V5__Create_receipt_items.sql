CREATE TABLE IF NOT EXISTS Receipt_items (
id INTEGER PRIMARY KEY,
name TEXT NOT NULL,
quantity REAL NOT NULL,
regular_price REAL NOT NULL,
discount REAL,
receipt_id INTEGER NOT NULL,
product_id INTEGER,
expense_category_id INTEGER NOT NULL,
FOREIGN KEY(receipt_id) REFERENCES receipts(id),
FOREIGN KEY(product_id) REFERENCES products(id),
FOREIGN KEY(expense_category_id) REFERENCES categories(id)
)

