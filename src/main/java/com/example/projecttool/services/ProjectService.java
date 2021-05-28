package com.example.projecttool.services;

import com.example.projecttool.models.project.Project;
import com.example.projecttool.repositories.ProjectRepository;
import com.example.projecttool.repositories.ShareProjectRepository;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProjectService {


    public static Project createProject(int userId, String name, String startDate, String endDate) throws SQLException {


        // Creates a project
        Project project = new Project(name, startDate, endDate);
        int project_id = ProjectRepository.createProject(userId, project);


        return new Project(project_id, name, startDate, endDate);
    }

    public static ArrayList<Project> seeProjectList(int userId) throws SQLException {


        return ProjectRepository.getProjects(userId);
    }


    public static Project getProject(int projectId) throws SQLException {

        return ProjectRepository.getProject(projectId);
    }

    public static boolean isReadOnly(int projectId, int userId) throws SQLException {
        String accessLevel = ShareProjectRepository.getAccessLevel(projectId, userId);
        return accessLevel.equals("read-only");

    }

    public static void deleteProject(int projectId) throws SQLException {

            ProjectRepository.deleteProject(projectId);
    }

    public static boolean isOwner(int projectId, int userId) throws SQLException{
        int ownerId = ProjectRepository.getOwnerId(projectId);
        return ownerId == userId;
    }


    public static boolean hasAccess(int projectId, int userId) throws SQLException{
        String accessLevel = ShareProjectRepository.getAccessLevel(projectId, userId);
        boolean isCollaborator = accessLevel.equals("read-and-edit") || accessLevel.equals("read-only");
        boolean isOwner = isOwner(projectId, userId);
        return isOwner || isCollaborator;
    }

    public static boolean canEdit(int projectId, int userId) throws SQLException{
        return hasAccess(projectId, userId) && !isReadOnly(projectId, userId);
    }

}

