create table if not exists orders
(
    id          bigserial   not null primary key,
    price       decimal     not null,
    created_on  timestamp   not null default now(),
    customer_id bigint      not null,
    state       varchar(50) not null default 'PENDING'
);

create table if not exists orders_products
(
    order_id   bigint not null,
    product_id bigint not null,
    primary key (order_id, product_id),
    constraint order_id_fk foreign key (order_id) references orders (id),
    constraint product_id_check check ( product_id > 0 )
);
