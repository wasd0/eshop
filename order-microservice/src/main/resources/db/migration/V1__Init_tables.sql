create table if not exists brands
(
    id              bigserial    not null primary key,
    title           varchar(100) not null,
    description     varchar(255),
    logo_image_path varchar(255)
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
    id bigserial not null primary key,
    organization varchar(255),
    address_id bigint,
    TIN int not null unique,
    
);

create table if not exists products
(

);