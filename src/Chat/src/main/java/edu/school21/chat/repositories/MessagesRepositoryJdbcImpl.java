package edu.school21.chat.repositories;

import com.zaxxer.hikari.HikariDataSource;
import edu.school21.chat.models.Message;

import javax.sql.DataSource;
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
                message.setAuthorMessage(resultSet.getInt("author"));
                message.setIdRoom(resultSet.getInt("room"));
                message.setText(resultSet.getString("text"));
                message.setDate(resultSet.getTimestamp("date_time"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.of(message);
    }

    public MessagesRepositoryJdbcImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }
}
