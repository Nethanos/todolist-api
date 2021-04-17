INSERT INTO db_user values ('1', 'johnDoe', 'jd@gmail.com', '12324');
INSERT INTO db_user values ('2', 'jack', 'jackd@gmail.com', '65432');


insert into db_role values ('1', 'USER');
insert into db_role values ('2', 'ADMIN');

insert into users_roles (user_id, role_id) values ('1', '1'), ('2', '1');