insert into users (username, email, password)
values ('user', 'user@user.com', '$2a$12$WOgvYv9MEZ44fHusrg4Wa.iTvfWBxvDcsoQcQWq7O1sj.NUCQ/CAC'),
       ('admin', 'admin@admin.com', '$2a$12$CtTWVrBJuFGravhWXbkoa.J6vWG/ht/0CC5QoAPqm9L2vaJsWnxvi')
on conflict do nothing;

insert into roles (name)
values ('ROLE_USER'),
       ('ROLE_ADMIN')
on conflict do nothing;

insert into users_roles (user_id, role_id)
values (1, 1),
       (2, 2)
on conflict do nothing;