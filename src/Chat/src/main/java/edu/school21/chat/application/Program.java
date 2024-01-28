package edu.school21.chat.application;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import edu.school21.chat.models.Chat;
import edu.school21.chat.models.Message;
import edu.school21.chat.models.User;
import edu.school21.chat.repositories.MessagesRepository;
import edu.school21.chat.repositories.MessagesRepositoryJdbcImpl;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

public class Program {
    public static void main(String[] args) throws SQLException {
        HikariConfig config = new HikariConfig("/hikari.properties");
        HikariDataSource dataSource = new HikariDataSource(config);
        MessagesRepository messagesRepository = new MessagesRepositoryJdbcImpl(dataSource);

        User creator = new User(2L, "antoinco", "321", new ArrayList<Chat>(), new ArrayList<Chat>());
        User author = creator;
        Chat room = new Chat(3L, "kakich", 2L, Optional.of(creator), new ArrayList<Message>());
        Message newMessage = new Message(null, Optional.of(author), Optional.of(room), "ya plakichhhhh AHHAHA", Timestamp.valueOf("2024-11-02 12:23:45"));
        MessagesRepository messagesRepository1 = new MessagesRepositoryJdbcImpl(dataSource);
        messagesRepository1.save(newMessage);
    }
}
