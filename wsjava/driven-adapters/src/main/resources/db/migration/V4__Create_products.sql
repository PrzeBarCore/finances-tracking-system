CREATE TABLE IF NOT EXISTS Products (
id INTEGER PRIMARY KEY,
name TEXT NOT NULL,
producer TEXT NOT NULL,
quantity REAL NOT NULL,
unit TEXT NOT NULL,
product_category_id INTEGER NOT NULL,
default_expense_product_category_id INTEGER,
FOREIGN KEY(product_category_id) REFERENCES categories(id),
FOREIGN KEY(default_expense_product_category_id) REFERENCES categories(id)
)


