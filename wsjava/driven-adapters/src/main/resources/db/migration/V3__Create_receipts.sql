CREATE TABLE IF NOT EXISTS Receipts (
id INTEGER PRIMARY KEY,
issued_on_date_time TEXT NOT NULL,
total_value REAL NOT NULL,
containing_list_of_items TEXT NOT NULL
)
