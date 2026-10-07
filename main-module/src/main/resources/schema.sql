create table address (
    id integer primary key auto_increment,
    city varchar(128) not null,
    country varchar(128) not null,
    number smallint not null,
    postbox varchar(3),
    street varchar(128) not null,
    zipcode varchar(16) not null
);

create table beans
(
    id integer primary key auto_increment,
    text varchar(256) not null
);

create table company
(
    id integer primary key auto_increment,
    address_id integer,
    name varchar(64) unique not null,
    constraint fk_company_address FOREIGN KEY (address_id) REFERENCES address(id)
);

create table employee
(
    id integer primary key auto_increment,
    address_id integer,
    firstName varchar(64) unique not null,
    lastName varchar(64) unique not null,
    constraint fk_employee_address FOREIGN KEY (address_id) references address(id)
);

create table recruiter
(
    id integer primary key auto_increment,
    company_id integer,
    firstName varchar(64) unique not null,
    lastName varchar(64) unique not null,
    constraint fk_recruiter_company foreign key (company_id) references company(id)
);
