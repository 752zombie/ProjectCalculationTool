package com.example.projecttool.repositories;


import com.example.projecttool.models.project.Project;

import java.sql.*;
import java.util.ArrayList;

// Najla & Matthias

public class ShareProjectRepository {


    public static ArrayList<Project> getSharedProjects(int userId) throws SQLException {

        Connection connection = DatabaseConnection.getConnection();

        PreparedStatement statement = connection.prepareStatement("SELECT * \n" +
                "FROM projects\n" +
                "INNER JOIN collaborators\n" +
                "ON  projects.project_id = collaborators.project_id\n" +
                "WHERE collaborator_id = ?");
        statement.setInt(1, userId);
        return ProjectRepository.getProjects(statement);

    }

    public static void shareProject(String receiverMail, String editOrRead, int projectId) throws SQLException {

        Connection connection = DatabaseConnection.getConnection();

        int receiverId = getReceiverId(receiverMail);


        PreparedStatement statement = connection.prepareStatement("INSERT INTO collaborators (project_id, collaborator_id, access_level) VALUES (?, ?, ?)");
        statement.setInt(1, projectId);
        statement.setInt(2, receiverId);
        statement.setString(3, editOrRead);

        statement.execute();
    }


    private static int getReceiverId(String receiverMail) throws SQLException {

        Connection connection = DatabaseConnection.getConnection();

        int receiverId = 0;


        PreparedStatement statement = connection.prepareStatement("SELECT user_id FROM users WHERE email = ?");
        statement.setString(1, receiverMail);
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            return resultSet.getInt("user_id");
        }

        return receiverId;

    }


    public static String getAccessLevel(int projectId, int userId) throws SQLException {

        Connection connection = DatabaseConnection.getConnection();
        String accessLevel = "no-access";

        PreparedStatement statement = connection.prepareStatement("SELECT * FROM collaborators WHERE project_id = ? AND collaborator_id = ?");
        statement.setInt(1, projectId);
        statement.setInt(2, userId);
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            accessLevel = resultSet.getString("access_level");
        }

        return accessLevel;

    }

    public static void ignoreProject(Integer projectId, int userId) throws SQLException {
        Connection connection = DatabaseConnection.getConnection();

        PreparedStatement statement = connection.prepareStatement("DELETE FROM collaborators WHERE collaborator_id = ? AND project_id = ?");
        statement.setInt(1, userId);
        statement.setInt(2, projectId);
        statement.execute();

    }
}







