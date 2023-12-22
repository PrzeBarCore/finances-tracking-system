CREATE TABLE IF NOT EXISTS Categories (
id INTEGER PRIMARY KEY,
name TEXT NOT NULL,
category_type TEXT NOT NULL,
parent_category_id INTEGER,
FOREIGN KEY (parent_category_id) REFERENCES categories(id)
)