package edu.school21.chat.application;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import edu.school21.chat.models.Message;
import edu.school21.chat.models.User;
import edu.school21.chat.repositories.MessagesRepository;
import edu.school21.chat.repositories.MessagesRepositoryJdbcImpl;
import edu.school21.chat.repositories.UsersRepository;
import edu.school21.chat.repositories.UsersRepositoryJdbcImpl;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class Program {
    public static void main(String[] args) throws SQLException {
        HikariConfig config = new HikariConfig("/hikari.properties");
        HikariDataSource dataSource = new HikariDataSource(config);

        UsersRepository usersRepository = new UsersRepositoryJdbcImpl(dataSource);
        List<User> all = usersRepository.findAll(0, 2);
        for(var user : all) {
            System.out.println(user);
            System.out.println();
        }


    }
}
