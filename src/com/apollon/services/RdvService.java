package com.apollon.services;

import com.apollon.entities.Rdv;
import com.apollon.utils.DatabaseConnection;
import com.apollon.utils.RelationObject;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class RdvService {

    private static RdvService instance;
    PreparedStatement preparedStatement;
    Connection connection;

    public RdvService() {
        connection = DatabaseConnection.getInstance().getConnection();
    }

    public static RdvService getInstance() {
        if (instance == null) {
            instance = new RdvService();
        }
        return instance;
    }

    public List<Rdv> getAll() {
        List<Rdv> listRdv = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM `rdv` AS r RIGHT JOIN `users` AS u ON r.user_id = u.id RIGHT JOIN `users` AS p ON r.partenaire_id = p.id  WHERE r.user_id = u.id");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                listRdv.add(new Rdv(
                        resultSet.getInt("id"),
                        new RelationObject(resultSet.getInt("user_id"), resultSet.getString("u.email")),
                        new RelationObject(resultSet.getInt("partenaire_id"), resultSet.getString("p.email")),
                        LocalDateTime.of(resultSet.getDate("debut").toLocalDate(), resultSet.getTime("debut").toLocalTime()),
                        LocalDateTime.of(resultSet.getDate("fin").toLocalDate(), resultSet.getTime("fin").toLocalTime()),
                        resultSet.getInt("etat"),
                        LocalDateTime.of(resultSet.getDate("created_at").toLocalDate(), resultSet.getTime("created_at").toLocalTime()),
                        LocalDateTime.of(resultSet.getDate("updated_at").toLocalDate(), resultSet.getTime("updated_at").toLocalTime())
                ));
            }
        } catch (SQLException exception) {
            System.out.println("Error (getAll) rdv : " + exception.getMessage());
        }
        return listRdv;
    }

    public List<RelationObject> getAllUsers() {
        List<RelationObject> listUsers = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM `users`");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                listUsers.add(new RelationObject(resultSet.getInt("id"), resultSet.getString("email")));
            }
        } catch (SQLException exception) {
            System.out.println("Error (getAll) users : " + exception.getMessage());
        }
        return listUsers;
    }

    public boolean checkExist(Rdv rdv) {
        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM `rdv` WHERE `user_id` = ? AND `partenaire_id` = ? AND `debut` = ? AND `fin` = ?");

            preparedStatement.setInt(1, rdv.getUser().getId());
            preparedStatement.setInt(2, rdv.getPartenaire().getId());
            preparedStatement.setTimestamp(3, Timestamp.valueOf(rdv.getDebut()));
            preparedStatement.setTimestamp(4, Timestamp.valueOf(rdv.getFin()));

            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();

        } catch (SQLException exception) {
            System.out.println("Error (getAll) sdp : " + exception.getMessage());
        }
        return false;
    }

    public boolean add(Rdv rdv) {
        if (checkExist(rdv)) {
            return false;
        }

        String request = "INSERT INTO `rdv`(`user_id`, `partenaire_id`, `debut`, `fin`, `etat`, `created_at`, `updated_at`) VALUES(?, ?, ?, ?, ?, ?, ?)";
        try {
            preparedStatement = connection.prepareStatement(request);

            preparedStatement.setInt(1, rdv.getUser().getId());
            preparedStatement.setInt(2, rdv.getPartenaire().getId());
            preparedStatement.setTimestamp(3, Timestamp.valueOf(rdv.getDebut()));
            preparedStatement.setTimestamp(4, Timestamp.valueOf(rdv.getFin()));
            preparedStatement.setInt(5, rdv.getEtat());
            preparedStatement.setTimestamp(6, Timestamp.valueOf(rdv.getCreatedAt()));
            preparedStatement.setTimestamp(7, Timestamp.valueOf(rdv.getUpdatedAt()));

            preparedStatement.executeUpdate();
            System.out.println("Rdv added");
            return true;
        } catch (SQLException exception) {
            System.out.println("Error (add) rdv : " + exception.getMessage());
        }
        return false;
    }

    public boolean edit(Rdv rdv) {
        if (checkExist(rdv)) {
            return false;
        }

        String request = "UPDATE `rdv` SET `user_id` = ?, `partenaire_id` = ?, `debut` = ?, `fin` = ?, `etat` = ?, `created_at` = ?, `updated_at` = ? WHERE `id`=" + rdv.getId();
        try {
            preparedStatement = connection.prepareStatement(request);

            preparedStatement.setInt(1, rdv.getUser().getId());
            preparedStatement.setInt(2, rdv.getPartenaire().getId());
            preparedStatement.setTimestamp(3, Timestamp.valueOf(rdv.getDebut()));
            preparedStatement.setTimestamp(4, Timestamp.valueOf(rdv.getFin()));
            preparedStatement.setInt(5, rdv.getEtat());
            preparedStatement.setTimestamp(6, Timestamp.valueOf(rdv.getCreatedAt()));
            preparedStatement.setTimestamp(7, Timestamp.valueOf(rdv.getUpdatedAt()));

            preparedStatement.executeUpdate();
            System.out.println("Rdv edited");
            return true;
        } catch (SQLException exception) {
            System.out.println("Error (edit) rdv : " + exception.getMessage());
        }
        return false;
    }

    public boolean delete(int id) {
        try {
            preparedStatement = connection.prepareStatement("DELETE FROM `rdv` WHERE `id`=?");
            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();
            preparedStatement.close();
            System.out.println("Rdv deleted");
            return true;
        } catch (SQLException exception) {
            System.out.println("Error (delete) rdv : " + exception.getMessage());
        }
        return false;
    }
}
