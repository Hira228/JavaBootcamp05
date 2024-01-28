package edu.school21.chat.repositories;

import com.zaxxer.hikari.HikariDataSource;
import edu.school21.chat.models.Chat;
import edu.school21.chat.models.Message;
import edu.school21.chat.models.User;

import javax.sql.DataSource;
import java.net.UnknownServiceException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import static java.sql.Connection.TRANSACTION_READ_COMMITTED;

public class MessagesRepositoryJdbcImpl implements MessagesRepository {
    private DataSource dataSource;

    @Override
    public Optional<Message> findById(Long id) throws SQLException {
        Connection connection = null;
        Message message = null;
        try {
            connection = dataSource.getConnection();
            connection.setTransactionIsolation(TRANSACTION_READ_COMMITTED);
            PreparedStatement prepareStatement = connection.prepareStatement("select * from mama.Message" +
                    " where id = ?");
            prepareStatement.setLong(1, id);
            ResultSet resultSet = prepareStatement.executeQuery();

            while(resultSet.next()) {
                message = new Message();
                message.setId(resultSet.getInt("id"));
                message.setAuthor(getUser(resultSet.getInt("author")));
                message.setChat(getChat(resultSet.getInt("room")));
                message.setText(resultSet.getString("text"));
                message.setDate(resultSet.getTimestamp("date_time"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.of(message);
    }

    private Optional<User> getUser(int idUser) throws SQLException {
        Connection connection = dataSource.getConnection();
        connection.setTransactionIsolation(TRANSACTION_READ_COMMITTED);
        PreparedStatement prepareStatement = connection.prepareStatement("select * from mama.User" +
                " where id = ?");
        prepareStatement.setLong(1 ,idUser);
        ResultSet resultSet = prepareStatement.executeQuery();
        User user = new User();
        while(resultSet.next()) {
            user.setId(resultSet.getInt("id"));
            user.setLogin(resultSet.getString("login"));
            user.setPassword(resultSet.getString("password"));
        }
        return Optional.of(user);
    }

    private Optional<Chat> getChat(int idChat) throws SQLException {
        Connection connection = dataSource.getConnection();
        connection.setTransactionIsolation(TRANSACTION_READ_COMMITTED);
        PreparedStatement preparedStatement = connection.prepareStatement("select * from mama.Chatroom"+
                " where id = ?");
        preparedStatement.setLong(1, idChat);
        ResultSet resultSet = preparedStatement.executeQuery();
        Chat chat = new Chat();
        while(resultSet.next()) {
            chat.setId(resultSet.getInt("id"));
            chat.setNameChat(resultSet.getString("Chatroom_name"));
            chat.setOwner(getUser(resultSet.getInt("owner")));
        }
        return Optional.of(chat);
    }

    public MessagesRepositoryJdbcImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }
}
