create table public.role (
    id serial primary key,
    name varchar(255) not null unique
);

create table public."user" (
    id serial primary key,
    login varchar(255) not null unique,
    password varchar(255) not null,
    username varchar(255) not null unique
);

create table public.user_role (
    user_id int not null,
    role_id int not null,
    primary key (user_id, role_id),
    foreign key (user_id) references "user"(id),
    foreign key (role_id) references role(id)
);

INSERT INTO public.role (id, name) VALUES (1, 'user');
INSERT INTO public.role (id, name) VALUES (2, 'manager');
INSERT INTO public.role (id, name) VALUES (3, 'admin');

INSERT INTO public."user" (id, login, password, username) VALUES (1, 'qwe', 'qwe', 'qwe1');
INSERT INTO public."user" (id, login, password, username) VALUES (2, 'asd', 'asd', 'asd1');
INSERT INTO public."user" (id, login, password, username) VALUES (3, 'zxc', 'zxc', 'zxc1');
INSERT INTO public."user" (id, login, password, username) VALUES (4, 'adm', 'adm', 'adm1');

INSERT INTO public.user_role (user_id, role_id) VALUES (1, 1);
INSERT INTO public.user_role (user_id, role_id) VALUES (2, 1);
INSERT INTO public.user_role (user_id, role_id) VALUES (2, 2);
INSERT INTO public.user_role (user_id, role_id) VALUES (3, 1);
INSERT INTO public.user_role (user_id, role_id) VALUES (4, 1);
INSERT INTO public.user_role (user_id, role_id) VALUES (4, 2);
INSERT INTO public.user_role (user_id, role_id) VALUES (4, 3);
