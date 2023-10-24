CREATE TABLE users
(
    id         SERIAL PRIMARY KEY,
    email      varchar(50),
    name       varchar(50) NOT NULL,
    surname    varchar(50) NOT NULL,
    patronymic varchar(50) NOT NULL,
    phone      varchar(25) NOT NULL
);

CREATE TABLE departments
(
    id      SERIAL PRIMARY KEY,
    title   varchar(100) NOT NULL,
    address varchar(150) NOT NULL
);

CREATE TABLE employees
(
    id                 SERIAL PRIMARY KEY,
    email              varchar(50),
    name               varchar(50) NOT NULL,
    surname            varchar(50) NOT NULL,
    patronymic         varchar(50) NOT NULL,
    phone              varchar(25) NOT NULL,
    experience         int,
    hired              date        NOT NULL,
    fired              date,
    work_department_id int         NOT NULL REFERENCES departments (id)
);

CREATE TABLE orders
(
    id                      SERIAL PRIMARY KEY,
    title                   varchar(150)   NOT NULL,
    type                    varchar(50)    NOT NULL,
    parcel_price            decimal(10, 2) NOT NULL,
    delivery_price          decimal(10, 2) NOT NULL,
    total_price             decimal(10, 2) NOT NULL,
    sender_id               int            NOT NULL REFERENCES users (id),
    recipient_id            int            NOT NULL REFERENCES users (id),
    department_sender_id    int            NOT NULL REFERENCES departments (id),
    department_recipient_id int            NOT NULL REFERENCES departments (id),
    employee_id             int            NOT NULL REFERENCES employees (id),
    status                  varchar(25)    NOT NULL
);