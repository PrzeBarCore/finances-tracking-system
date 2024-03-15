CREATE TABLE IF NOT EXISTS Transactions(
   id INTEGER PRIMARY KEY,
   issued_on_date_time TIMESTAMP  NOT NULL,
   total_value REAL NOT NULL,
   transaction_type TEXT NOT NULL,
   description TEXT,
   repayment_date TIMESTAMP ,
   transaction_category_id INTEGER,
   target_account_id INTEGER,
   source_account_id INTEGER,
   receipt_id INTEGER,
   FOREIGN KEY (transaction_category_id) REFERENCES categories(id),
   FOREIGN KEY (target_account_id) REFERENCES accounts(id),
   FOREIGN KEY (source_account_id) REFERENCES accounts(id),
   FOREIGN KEY (receipt_id) REFERENCES receipts(id)
   )