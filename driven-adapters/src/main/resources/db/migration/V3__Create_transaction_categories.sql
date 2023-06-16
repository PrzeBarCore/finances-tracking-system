CREATE TABLE Transaction_categories (
id int not null PRIMARY KEY auto_increment,
name varchar(100) NOT NULL,
transaction_type varchar(15) NOT NULL,
dependency_level int NOT NULL,
parent_category_id int
)