package com.apollon.services;

import com.apollon.entities.Abo;
import com.apollon.utils.DatabaseConnection;
import com.apollon.utils.RelationObject;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AboService {

    private static AboService instance;
    PreparedStatement preparedStatement;
    Connection connection;

    public AboService() {
        connection = DatabaseConnection.getInstance().getConnection();
    }

    public static AboService getInstance() {
        if (instance == null) {
            instance = new AboService();
        }
        return instance;
    }

    public List<Abo> getAll() {
        List<Abo> listAbo = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM `abo` AS a RIGHT JOIN `users` AS u ON a.user_id = u.id RIGHT JOIN `salle_de_sport` AS s ON a.sdp_id = s.id WHERE a.user_id = u.id AND a.sdp_id = s.id");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                listAbo.add(new Abo(
                        resultSet.getInt("id"),
                        LocalDate.parse(String.valueOf(resultSet.getDate("created_at"))),
                        new RelationObject(resultSet.getInt("user_id"), resultSet.getString("u.nom")),
                        new RelationObject(resultSet.getInt("sdp_id"), resultSet.getString("s.nom_salle")),
                        resultSet.getString("duree"),
                        resultSet.getString("etat")

                ));
            }
        } catch (SQLException exception) {
            System.out.println("Error (getAll) abo : " + exception.getMessage());
        }
        return listAbo;
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
            System.out.println("Error (getAll) sdp : " + exception.getMessage());
        }
        return listUsers;
    }

    public List<RelationObject> getAllSdp() {
        List<RelationObject> listUsers = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM `salle_de_sport`");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                listUsers.add(new RelationObject(resultSet.getInt("id"), resultSet.getString("nom_salle")));
            }
        } catch (SQLException exception) {
            System.out.println("Error (getAll) users : " + exception.getMessage());
        }
        return listUsers;
    }

    public boolean add(Abo abo) {
        String request = "INSERT INTO `abo`(`created_at`, `user_id`, `sdp_id`, `duree`, `etat`) VALUES(?, ?, ?, ?, ?)";
        try {
            preparedStatement = connection.prepareStatement(request);
            System.out.println(abo.getUser().getId());
            preparedStatement.setDate(1, Date.valueOf(abo.getCreatedAt()));
            preparedStatement.setInt(2, abo.getUser().getId());
            preparedStatement.setInt(3, abo.getSdp().getId());
            preparedStatement.setString(4, abo.getDuree());
            preparedStatement.setString(5, abo.getEtat());

            preparedStatement.executeUpdate();
            System.out.println("Abo added");
            return true;
        } catch (SQLException exception) {
            System.out.println("Error (add) abo : " + exception.getMessage());
        }
        return false;
    }

    public boolean edit(Abo abo) {
        String request = "UPDATE `abo` SET `created_at` = ?, `user_id` = ?, `sdp_id` = ?, `duree` = ?, `etat` = ? WHERE `id`=" + abo.getId();
        try {
            preparedStatement = connection.prepareStatement(request);

            preparedStatement.setDate(1, Date.valueOf(abo.getCreatedAt()));
            preparedStatement.setInt(2, abo.getUser().getId());
            preparedStatement.setInt(3, abo.getSdp().getId());
            preparedStatement.setString(4, abo.getDuree());
            preparedStatement.setString(5, abo.getEtat());

            preparedStatement.executeUpdate();
            System.out.println("Abo edited");
            return true;
        } catch (SQLException exception) {
            System.out.println("Error (edit) abo : " + exception.getMessage());
        }
        return false;
    }

    public boolean delete(int id) {
        try {
            preparedStatement = connection.prepareStatement("DELETE FROM `abo` WHERE `id`=?");
            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();
            preparedStatement.close();
            System.out.println("Abo deleted");
            return true;
        } catch (SQLException exception) {
            System.out.println("Error (delete) abo : " + exception.getMessage());
        }
        return false;
    }
}

