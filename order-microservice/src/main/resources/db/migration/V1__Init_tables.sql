create table if not exists brands
(
    id          serial       not null primary key,
    title       varchar(100) not null,
    description varchar(255)
);

create table if not exists categories
(
    id                 bigserial    not null primary key,
    title              varchar(100) not null,
    parent_category_id bigint,
    constraint parent_category_id_fk foreign key (parent_category_id) references categories (id)
);

create table if not exists sellers
(
    id          bigserial    not null primary key,
    title       varchar(255) not null,
    description varchar(300),
    TIN         int          not null unique
);

create table if not exists order_details
(
    id          bigserial   not null primary key,
    state       varchar(50) not null default 'PENDING',
    version     bigint      not null,
    seller_id   bigint      not null,
    category_id bigint      not null,
    brand_id    int         not null,
    constraint seller_id_fk foreign key (seller_id) references sellers (id),
    constraint category_id_fk foreign key (category_id) references categories (id),
    constraint brand_id_fk foreign key (brand_id) references brands (id)
);

create table if not exists orders
(
    id          bigint  not null unique,
    price       decimal not null,
    created_on  timestamp default now(),
    customer_id bigint  not null,
    constraint details_id_fk foreign key (id) references order_details (id)
);

create index if not exists order_brands_index on order_details (brand_id);
create index if not exists order_categories_index on order_details (category_id);
create index if not exists order_sellers_index on order_details (seller_id);