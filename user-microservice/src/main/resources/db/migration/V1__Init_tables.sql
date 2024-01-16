create table users
(
    id bigserial primary key,
    username varchar(50) not null unique,
    email varchar(100) not null,
    password varchar(100) not null,
    registered_at date not null default current_date
);

create table roles
(
    id bigserial primary key,
    name varchar(50) not null unique
);

create table users_roles
(
    user_id bigint not null,
    role_id bigint not null,
    constraint user_id_fk foreign key (user_id) references users (id),
    constraint role_id_fk foreign key (role_id) references roles (id)
);