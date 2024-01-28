package edu.school21.chat.repositories;

import edu.school21.chat.models.Chat;
import edu.school21.chat.models.User;

import javax.crypto.AEADBadTagException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.sql.Connection.TRANSACTION_READ_COMMITTED;

public class UsersRepositoryJdbcImpl implements UsersRepository {
    DataSource dataSource;

    public UsersRepositoryJdbcImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<User> findAll(int page, int size) {
        List<User> listUser = new ArrayList<>();
        try {
            MessagesRepositoryJdbcImpl messagesRepository = new MessagesRepositoryJdbcImpl(dataSource);

            int startPage = page * size;
            int endPage = startPage + size;
            Connection connection = dataSource.getConnection();
            connection.setTransactionIsolation(TRANSACTION_READ_COMMITTED);
            PreparedStatement preparedStatement = connection.prepareStatement("WITH paginated_users AS ( " +
                    "SELECT u.id as id_user, ucr.id_chatroom, c.id as created_room " +
                    "FROM mama.User as u " +
                    "FULL JOIN mama.UserChatRoom as ucr ON ucr.id_user = u.id " +
                    "FULL JOIN mama.Chatroom as c ON c.owner = u.id " +
                    ") " +
                    "SELECT * FROM paginated_users " +
                    "WHERE id_user BETWEEN ? AND ?");
            preparedStatement.setInt(1, startPage);
            preparedStatement.setInt(2, endPage);
            ResultSet resultSet = preparedStatement.executeQuery();
            User user = null;
            while (resultSet.next()) {
                Optional<Chat> currentActiveChat = messagesRepository.getChat(resultSet.getInt("id_chatroom"));
                Optional<Chat> currentOwnerChat = messagesRepository.getChat(resultSet.getInt("created_room"));
                if (user != null && user.getId() == resultSet.getInt("id_user")) {
                    if(currentActiveChat.isPresent() && !user.getActiveChats().contains(currentActiveChat)) {
                        user.getActiveChats().add(currentActiveChat.get());
                    }
                    if(currentOwnerChat.isPresent() && !user.getChatsOwner().contains(currentOwnerChat)) {
                        user.getActiveChats().add(currentOwnerChat.get());
                    }
                } else {
                    user = new User(messagesRepository.getUser(resultSet.getInt("id_user")).get());
                    if(currentActiveChat.isPresent()) user.getActiveChats().add(currentActiveChat.get());
                    if(currentOwnerChat.isPresent()) user.getChatsOwner().add(currentOwnerChat.get());
                    listUser.add(user);
                }
            }
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listUser;
    }

    public Optional<User> getUser(int idUser) throws SQLException {
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
        connection.close();
        return Optional.of(user);
    }

    public Optional<Chat> getChat(int idChat) throws SQLException {
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
        connection.close();
        return Optional.of(chat);
    }

}
