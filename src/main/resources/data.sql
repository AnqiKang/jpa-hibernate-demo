insert into course(id, name, created_date, last_updated_date)
values (10001, 'JPA in 50 steps', sysdate(), sysdate());
insert into course(id, name, created_date, last_updated_date)
values (10002, 'SpringBoot', sysdate(), sysdate());
insert into course(id, name, created_date, last_updated_date)
values (10003, 'MicroService', sysdate(), sysdate());

insert into passport(id, number)
values (40001, 'E5895');
insert into passport(id, number)
values (40002, 'E1403');
insert into passport(id, number)
values (40003, 'E3687');

insert into student(id, name, passport_id)
values (20001, 'Karen', 40001);
insert into student(id, name, passport_id)
values (20002, 'Tom', 40002);
insert into student(id, name, passport_id)
values (20003, 'Jingyu', 40003);



insert into review(id, rating, description, course_id)
values (50001, '5', 'Great Course', 10001);
insert into review(id, rating, description, course_id)
values (50002, '4', 'Wonderful Course', 10001);
insert into review(id, rating, description, course_id)
values (50003, '5', 'Awesome Course', 10001);