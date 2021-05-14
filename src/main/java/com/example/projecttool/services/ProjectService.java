package com.example.projecttool.services;

import com.example.projecttool.models.User;
import com.example.projecttool.models.project.Project;
import com.example.projecttool.repositories.ProjectRepository;
import com.example.projecttool.repositories.ShareProjectRepository;
import com.example.projecttool.repositories.TaskRepository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProjectService {


    public Project nameYourProject(int userId, String projectName, String projectStart, String projectEnd) throws SQLException {


        // Creates a project
        int project_id = ProjectRepository.createProject(userId, projectName, projectStart, projectEnd);

        // Saves project in session and adds to view
       Project project = new Project(project_id, projectName, projectStart, projectEnd);


       return project;
    }

    public ArrayList<Project> seeProjectList(int userId) throws SQLException {

        ArrayList<Project> allProjects = ProjectRepository.getProjects(userId);


        return allProjects;
    }


    public Project getProject(int projectId) throws SQLException {

        return ProjectRepository.getProject(projectId);
    }

    public boolean isReadOnly(int projectId, int userId) throws SQLException {

        return ShareProjectRepository.isReadOnly(projectId, userId);
    }

    public void deleteProject(Integer projectId) throws SQLException {

            ProjectRepository.deleteProject(projectId);
        }

    }

