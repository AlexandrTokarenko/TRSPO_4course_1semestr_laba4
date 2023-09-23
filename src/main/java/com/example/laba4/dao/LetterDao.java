package com.example.laba4.dao;

import com.example.laba4.data.Letter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class LetterDao {

    @Autowired
    private DataSource dataSource;

    public Letter findById(int id) {

        String SELECT = "select * from letter where id=? ";
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement(SELECT);
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int senderId = resultSet.getInt("sender_id");
                int receiverId = resultSet.getInt("receiver_id");
                String topic = resultSet.getString("topic");
                String text = resultSet.getString("text");
                Date dateOfShipment = resultSet.getDate("date_of_shipment");

                return new Letter(id,senderId,receiverId,topic,text,dateOfShipment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public List<Letter> findAllBySenderId(int senderId) {

        String SELECT = "select * from letter where sender_id=? ";
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        List<Letter> letters = new ArrayList<>();
        try {
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement(SELECT);
            preparedStatement.setInt(1,senderId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int receiverId = resultSet.getInt("receiver_id");
                String topic = resultSet.getString("topic");
                String text = resultSet.getString("text");
                Date dateOfShipment = resultSet.getDate("date_of_shipment");

                letters.add(new Letter(id,senderId,receiverId,topic,text,dateOfShipment));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return letters;
    }

    public List<Letter> findAllByReceiverId(int receiverId) {

        String SELECT = "select * from letter where receiver_id=? ";
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        List<Letter> letters = new ArrayList<>();
        try {
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement(SELECT);
            preparedStatement.setInt(1,receiverId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int senderId = resultSet.getInt("sender_id");
                String topic = resultSet.getString("topic");
                String text = resultSet.getString("text");
                Date dateOfShipment = resultSet.getDate("date_of_shipment");

                letters.add(new Letter(id,senderId,receiverId,topic,text,dateOfShipment));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return letters;
    }

    public void save(Letter letter) {

        String INSERT = "insert into letter(sender_id,receiver_id,topic,text,date_of_shipment) values(?,?,?,?,?)";
        PreparedStatement preparedStatement;
        Connection connection;
        try {
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement(INSERT);

            preparedStatement.setInt(1,letter.getSenderId());
            preparedStatement.setInt(2,letter.getReceiverId());
            preparedStatement.setString(3,letter.getTopic());
            preparedStatement.setString(4,letter.getText());
            preparedStatement.setDate(5,letter.getDateOfShipment());

            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateWithoutSenderId(Letter letter) {

        String UPDATE = "update letter set topic=?,receiver_id=?, text=? where id=?";
        PreparedStatement preparedStatement;
        Connection connection;

        try {
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement(UPDATE);

            preparedStatement.setString(1,letter.getTopic());
            preparedStatement.setInt(2,letter.getReceiverId());
            preparedStatement.setString(3,letter.getText());
            preparedStatement.setInt(4,letter.getId());

            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteById(int letterId) {

        String DELETE = "delete from letter where id=?";
        PreparedStatement preparedStatement;
        Connection connection;

        try {
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement(DELETE);

            preparedStatement.setInt(1,letterId);

            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Letter> findAllBySenderIdOrderByDate(int senderId) {
        String SELECT = "select * from letter where sender_id=? order by date_of_shipment asc ";
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        List<Letter> letters = new ArrayList<>();
        try {
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement(SELECT);
            preparedStatement.setInt(1,senderId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int receiverId = resultSet.getInt("receiver_id");
                String topic = resultSet.getString("topic");
                String text = resultSet.getString("text");
                Date dateOfShipment = resultSet.getDate("date_of_shipment");

                letters.add(new Letter(id,senderId,receiverId,topic,text,dateOfShipment));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return letters;    }

    public List<Letter> findAllByReceiverIdOrderByDate(int receiverId) {

        String SELECT = "select * from letter where receiver_id=? order by date_of_shipment asc";
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        List<Letter> letters = new ArrayList<>();
        try {
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement(SELECT);
            preparedStatement.setInt(1,receiverId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int senderId = resultSet.getInt("sender_id");
                String topic = resultSet.getString("topic");
                String text = resultSet.getString("text");
                Date dateOfShipment = resultSet.getDate("date_of_shipment");

                letters.add(new Letter(id,senderId,receiverId,topic,text,dateOfShipment));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return letters;
    }
}
