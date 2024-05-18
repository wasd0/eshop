insert into categories (title)
values ('Electronics')
on conflict do nothing;
insert into sellers (title, description, tin)
values ('TestSeller', 'TestDesc', 123)
on conflict do nothing;
insert into brands (title, description)
values ('Sony', 'Electronics')
on conflict do nothing;