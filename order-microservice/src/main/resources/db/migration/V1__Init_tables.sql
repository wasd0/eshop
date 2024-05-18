create table if not exists orders
(
    id          bigserial   not null primary key,
    price       decimal     not null,
    created_on  timestamp   not null default now(),
    customer_id bigint      not null,
    state       varchar(50) not null default 'PENDING'
);

create table if not exists orders_history
(
    id             bigserial    not null primary key,
    order_id       bigint       not null,
    products       bigint[]     not null,
    description    varchar(300) not null,
    operation_time timestamp    not null default now(),
    constraint order_id_fk foreign key (order_id) references orders (id)
);
