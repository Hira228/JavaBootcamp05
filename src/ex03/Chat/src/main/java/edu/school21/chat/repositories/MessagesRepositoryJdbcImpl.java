package edu.school21.chat.repositories;

import com.zaxxer.hikari.HikariDataSource;
import edu.school21.chat.exceptions.NotSavedSubEntityException;
import edu.school21.chat.models.Chat;
import edu.school21.chat.models.Message;
import edu.school21.chat.models.User;

import javax.sql.DataSource;
import java.awt.image.ImagingOpException;
import java.net.UnknownServiceException;
import java.sql.*;
import java.util.ArrayList;
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

    @Override
    public void save(Message message) {
        Connection connection = null;
        User user = new User();
        try {
            connection = dataSource.getConnection();
            connection.setTransactionIsolation(TRANSACTION_READ_COMMITTED);
            Optional<User> author = getUser((int)message.getAuthor().get().getId());
            System.out.printf(author.get().getLogin());
            Optional<Chat> room = getChat((int)message.getChat().get().getId());
            System.out.println();
            System.out.println(message.getAuthor().get());
            if(!author.isEmpty() && !room.isEmpty()) {
                PreparedStatement prepareStatement = connection.prepareStatement(
                        "INSERT INTO mama.Message (author, room, text, date_time) " +
                                "VALUES ((SELECT id FROM mama.User WHERE login = ?), " +
                                "(SELECT id FROM mama.Chatroom WHERE Chatroom_name = ?), " +
                                "?, ?)", Statement.RETURN_GENERATED_KEYS);
                prepareStatement.setString(1, message.getAuthor().get().getLogin());
                prepareStatement.setString(2, message.getChat().get().getNameChat());
                prepareStatement.setString(3, message.getText());
                prepareStatement.setTimestamp(4, message.getDate());
                prepareStatement.executeUpdate();
                ResultSet resultSet = prepareStatement.getGeneratedKeys();
                if(resultSet.next()) {
                    message.setId(resultSet.getInt(1));
                    System.out.println(message.getId());
                }
            } else {
                throw new NotSavedSubEntityException("ебло");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Message message) {
        try {
            Connection connection = dataSource.getConnection();
            connection.setTransactionIsolation(TRANSACTION_READ_COMMITTED);
            PreparedStatement prepareStatement = connection.prepareStatement("update mama.Message " +
                    "set text = ?, date_time = ? " +
                    "where id = ?");
            prepareStatement.setString(1, message.getText());
            prepareStatement.setTimestamp(2, message.getDate());
            prepareStatement.setInt(3, (int)message.getId());
            prepareStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

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
