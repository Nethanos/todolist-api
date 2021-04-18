INSERT INTO db_user (id, username, email, password) values
('1', 'johnDoe', 'jd@gmail.com', '$2y$12$Isu5h7OrU66btes2J0bol.kqutrlwz..JnvVS85okS3E/7/iDnOj.'),
('2', 'jack', 'jackd@gmail.com', '$2y$12$Isu5h7OrU66btes2J0bol.kqutrlwz..JnvVS85okS3E/7/iDnOj.');


insert into db_role values ('1', 'USER');
insert into db_role values ('2', 'ADMIN');

insert into users_roles (user_id, role_id) values ('1', '1'), ('2', '1');