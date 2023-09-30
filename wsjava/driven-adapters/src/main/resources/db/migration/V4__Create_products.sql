CREATE TABLE Products (
id int not null PRIMARY KEY auto_increment,
name varchar(100) NOT NULL,
producer varchar(100) NOT NULL,
quantity double NOT NULL,
unit varchar(5) NOT NULL,
product_category_id int,
default_expense_product_category_id int,
FOREIGN KEY(product_category_id) REFERENCES categories(id),
FOREIGN KEY(default_expense_product_category_id) REFERENCES categories(id)
)


