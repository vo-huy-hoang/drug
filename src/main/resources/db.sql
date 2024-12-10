create database facebook_manager;
use facebook_manager;
create table role (
    id   int auto_increment primary key,
    name varchar(255) not null
);
insert into role (id, name)
values (1, 'ROLE_USER');

insert into user (id, birthday, email, from_to, gender, graduated, live_in, name, password, phone_number, quantity_friend, relationship_status, username, workplace)
values (1, '2005-01-12', 'admin@gmail.com', 'Viêt Nam', true, 'THPT Hoàng Hoa Thám', 'Đà Nẵng', 'Võ Huy Hoàng','$2a$12$tL0DK1hmuFMsr048gk/EPOy/Z1Ybici./KNjHE9Us/Fw7obyO0YVW', '0935271790', 100, 'Độc Thân', 'admin', 'tại nhà');
insert into user (id, birthday, email, from_to, gender, graduated, live_in, name, password, phone_number, quantity_friend, relationship_status, username, workplace)
values (2, '2004-02-13', 'guest@gmail.com', 'Viêt Nam', false, 'THPT Ngô Quyền', 'Đà Nẵng', 'Nguyễn Văn A','$2a$12$VLRr4yiFoV.i/prwOGTJlewEVl54rchLq7UUGnhZ1PfQH6nGje7g.', '0933276390', 100, 'Độc Thân', 'guest', 'công ty');

insert into user_role (user_id, role_id)
values (1, 1);
insert into user_role (user_id, role_id)
values (2, 1);

insert into post (id, body, is_edit, title, who_can_see, user_id)
values (1, 'hello', true, 'java', 'bạn bè', 1);
insert into post (id, body, is_edit, title, who_can_see, user_id)
values (2, 'hii', false, 'nodejs', 'bạn bè', 2);

insert into comment (id, content, post_id, user_id)
values (1, 'ok', 1, 1),
       (2, 'hay', 2, 2);

select u.username, p.title, p.body from Post as p join User as u on p.id = u.id;
SELECT u.id, u.username, p.title FROM Post p JOIN User u ON p.user_id = u.id;
select c.content, c.create_at, p.id, u.id, u.username from Comment as c join User as u on c.user_id = u.id join Post as p on c.post_id = p.id where post_id = ?;
select u.name, u.nickname, u.story, u.gender from user as u where u.id = ?;

select * from notification as n join user as u on n.user_id = u.id where u.email = ?;

select u.id, u.username, p.title, p.create_at FROM Post as p JOIN User u ON p.user_id = u.id;
select fr.id, u.id, u.username from follow_relation as fr join user u on u.id = fr.follower_id and u.id = fr.user_id where u.id != ?;
select u.id, u.username from user as u where u.id != ?;
SELECT u.id, u.username
FROM follow_relation AS fr
         JOIN user u ON u.id = fr.follower_id
WHERE u.id != (SELECT id FROM user WHERE username = ?);
select c.id, cu.content, cu.from_id, cu.to_id from conversation_user cu left join conversation c on cu.conversation_id = c.id where conversation_id = ?;
select c.id, cu.content, cu.from_id, cu.to_id, cu.create_at from conversation_user as cu left join conversation as c on cu.conversation_id = c.id where cu.conversation_id = ?;
select distinct cu.conversation_id, u.id from user u join conversation_user cu on u.id = cu.to_id where u.id != ?;