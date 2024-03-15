CREATE TABLE IF NOT EXISTS Receipts (
id INTEGER PRIMARY KEY,
issued_on_date_time TIMESTAMP NOT NULL,
total_value REAL NOT NULL,
total_discount REAL
)
