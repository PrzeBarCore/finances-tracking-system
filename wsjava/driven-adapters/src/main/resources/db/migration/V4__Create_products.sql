CREATE TABLE IF NOT EXISTS Products (
id INTEGER PRIMARY KEY,
name TEXT NOT NULL,
producer TEXT NOT NULL,
quantity REAL NOT NULL,
unit TEXT NOT NULL,
default_price REAL NOT NULL,
product_category_id INTEGER NOT NULL,
transaction_category_id INTEGER NOT NULL,
FOREIGN KEY(product_category_id) REFERENCES categories(id),
FOREIGN KEY(transaction_category_id) REFERENCES categories(id)
)


