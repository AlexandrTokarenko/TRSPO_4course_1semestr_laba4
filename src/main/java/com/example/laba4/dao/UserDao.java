package com.example.laba4.dao;

import com.example.laba4.data.Letter;
import com.example.laba4.data.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class UserDao {

    @Autowired
    private DataSource dataSource;

    public List<User> findAll() {

        PreparedStatement preparedStatement;
        Connection connection = null;
        String SELECT = "select * from users";
        List<User> users = new ArrayList<>();
        try {

            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement(SELECT);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String firsName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                String patronymic = resultSet.getString("patronymic");
                Date dateOfBirth = resultSet.getDate("date_of_birth");

                users.add(new User(id,firsName,lastName,patronymic,dateOfBirth));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return users;
    }

    public User findById(int userId) {

        PreparedStatement preparedStatement;
        Connection connection;

        String SELECT = "select * from users where id=?";

        try {
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement(SELECT);

            preparedStatement.setInt(1,userId);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String firsName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                String patronymic = resultSet.getString("patronymic");
                Date dateOfBirth = resultSet.getDate("date_of_birth");

                return new User(userId,firsName,lastName,patronymic,dateOfBirth);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
