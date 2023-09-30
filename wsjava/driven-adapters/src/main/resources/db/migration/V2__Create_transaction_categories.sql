CREATE TABLE Categories (
id int not null PRIMARY KEY auto_increment,
name varchar(100) NOT NULL,
category_type varchar(15) NOT NULL,
dependency_level int NOT NULL,
parent_category_id int
)