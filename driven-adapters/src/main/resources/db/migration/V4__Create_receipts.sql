CREATE TABLE Receipts (
id int not null PRIMARY KEY auto_increment,
issued_on_date_time timestamp NOT NULL,
total_value double NOT NULL,
containing_list_of_items varchar(1) NOT NULL
)
