create table if not exists sellers
(
    id          bigserial    not null primary key,
    title       varchar(255) not null,
    description varchar(300),
    TIN         int          not null unique,
    constraint check_tin_min check ( TIN > 0 )
);

create table if not exists brands
(
    id          serial       not null primary key,
    title       varchar(100) not null,
    description varchar(255)
);

create table if not exists categories
(
    id                 bigserial    not null primary key,
    title              varchar(100) not null unique,
    parent_category_id bigint,
    constraint parent_category_id_fk foreign key (parent_category_id) references categories (id)
);

create table if not exists products
(
    id          bigserial    not null primary key,
    title       varchar(100) not null,
    description text         not null,
    category_id bigint       not null,
    brand_id    int          not null,
    seller_id   bigint       not null,
    constraint category_id_fk foreign key (category_id) references categories (id),
    constraint brand_id_fk foreign key (brand_id) references brands (id),
    constraint seller_id_fk foreign key (seller_id) references sellers (id)
);

create table if not exists products_history
(
    id             bigserial    not null primary key,
    product_id     bigint       not null,
    quantity       int          not null,
    description    varchar(300) not null,
    operation_time timestamp    not null default now(),
    constraint product_id_fk foreign key (product_id) references products (id)
);

create index if not exists product_brands_index on products (brand_id);
create index if not exists product_categories_index on products (category_id);
create index if not exists history_products_index on products_history (product_id);