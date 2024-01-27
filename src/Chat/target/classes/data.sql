--insert to user
insert into mama.User(login,password) values (
'gradyzan',
'123'
)

insert into mama.User(login,password) values (
'antoinco',
'321'
)

insert into mama.User(login,password) values (
'kristlee',
'231'
)

insert into mama.User(login,password) values (
'kaleight',
'2314'
)

insert into mama.User(login,password) values (
'kaka',
'23144'
)

--insert to chatroom
insert into mama.Chatroom(Chatroom_name, owner) values (
'cyber',
(select id from mama.User
where login = 'gradyzan')
)

insert into mama.Chatroom(Chatroom_name, owner) values (
'nitik',
(select id from mama.User
where login = 'antoinco')
)

insert into mama.Chatroom(Chatroom_name, owner) values (
'kakich',
(select id from mama.User
where login = 'antoinco')
)

insert into mama.Chatroom(Chatroom_name, owner) values (
'speedrun',
(select id from mama.User
where login = 'kristlee')
)

insert into mama.Chatroom(Chatroom_name, owner) values (
'working',
(select id from mama.User
where login = 'kaleight')
)

--insert to message
insert into mama.Message(author, room, text, date_time) values (
(select id from mama.User
where login = 'kaleight'),
(select id from mama.Chatroom
where Chatroom_name = 'working'),
'всем привет',
'2024-10-01 10:21:31'
)--

insert into mama.Message(author, room, text, date_time) values (
(select id from mama.User
where login = 'antoinco'),
(select id from mama.Chatroom
where Chatroom_name = 'kakich'),
'ну я да какич',
'2024-11-02 12:23:11'
)--

insert into mama.Message(author, room, text, date_time) values (
(select id from mama.User
where login = 'antoinco'),
(select id from mama.Chatroom
where Chatroom_name = 'nitik'),
'а тут я нытич и тильтич',
'2024-11-02 12:25:11'
)

insert into mama.Message(author, room, text, date_time) values (
(select id from mama.User
where login = 'gradyzan'),
(select id from mama.Chatroom
where Chatroom_name = 'nitik'),
'ну да я знаю что ты нытич, беда',
'2024-11-02 12:25:11'
)--

insert into mama.Message(author, room, text, date_time) values (
(select id from mama.User
where login = 'gradyzan'),
(select id from mama.Chatroom
where Chatroom_name = 'cyber'),
'жесткий кибер',
'2024-11-02 12:27:11'
)--

--insert to userchatroom

insert into mama.UserChatRoom(id_user, id_chatroom) values (
(select id from mama.User
    where login = 'antoinco'),
(select id from mama.Chatroom
    where Chatroom_name = 'kakich')
)--

insert into mama.UserChatRoom(id_user, id_chatroom) values (
(select id from mama.User
    where login = 'gradyzan'),
(select id from mama.Chatroom
    where Chatroom_name = 'cyber')
)--

insert into mama.UserChatRoom(id_user, id_chatroom) values (
(select id from mama.User
    where login = 'gradyzan'),
(select id from mama.Chatroom
    where Chatroom_name = 'nitik')
)--

insert into mama.UserChatRoom(id_user, id_chatroom) values (
(select id from mama.User
    where login = 'kaleight'),
(select id from mama.Chatroom
    where Chatroom_name = 'working')
)--

