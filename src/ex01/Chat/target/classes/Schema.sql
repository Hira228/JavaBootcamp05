create schema mama;

create table mama.User(
id serial primary key,
login varchar not null,
password varchar not null
);

create table mama.Chatroom(
id serial primary key,
Chatroom_name varchar not null,
owner int references mama.User(id)
);

create table mama.Message(
id serial primary key,
author int references mama.User(id),
room int references mama.Chatroom(id),
text varchar not null,
date_time timestamp(0)
);

create table mama.UserChatRoom(
id_user int references mama.User(id),
id_chatroom int references mama.Chatroom(id),
primary key (id_user, id_chatroom)
)

select * from mama.User as u
join mama.Message as m on m.author = u.id

select * from mama.Message