create table if not exists users
(
    id bigserial primary key,
    username varchar(50) not null unique,
    email varchar(100) not null,
    password varchar(100) not null,
    registered_at timestamp not null default current_date
);

create table if not exists roles
(
    id bigserial primary key,
    name varchar(50) not null unique
);

create table if not exists users_roles
(
    user_id bigint not null,
    role_id bigint not null,
    constraint user_id_fk foreign key (user_id) references users (id),
    constraint role_id_fk foreign key (role_id) references roles (id)
);