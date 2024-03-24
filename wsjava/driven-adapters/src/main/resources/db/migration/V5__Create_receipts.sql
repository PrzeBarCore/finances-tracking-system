CREATE TABLE IF NOT EXISTS Receipts (
id INTEGER PRIMARY KEY,
total_discount REAL,
transaction_id INTEGER,
FOREIGN KEY (transaction_id) REFERENCES transactions(id)
)