package com.example.projecttool.services;

import com.example.projecttool.models.project.Employee;
import com.example.projecttool.models.project.Subtask;
import com.example.projecttool.repositories.ProjectRepository;
import com.example.projecttool.repositories.SubtaskRepository;
import com.example.projecttool.repositories.TaskRepository;

import java.sql.SQLException;
import java.util.ArrayList;

// Magnus

public class SubtaskService {
    public static ArrayList<Subtask> getSubtasks(int taskId) throws SQLException{
        return SubtaskRepository.getRelatedSubtasks(taskId);
    }

    public static void createSubtask(int taskId, String subtaskName, String subtaskDescription,
                                     String startDate, String endDate, int hoursToComplete,
                                     int projectId, int userId) throws SQLException {
        if (ProjectService.canEdit(projectId, userId)) {
            SubtaskRepository.addNewSubtaskToTask(taskId, new Subtask(subtaskName, subtaskDescription, java.sql.Date.valueOf(startDate), java.sql.Date.valueOf(endDate), hoursToComplete));
        }

    }

    public static void updateSubtask(int subtaskId, String name, String description, String startDate, String endDate,
                                     int hoursToComplete, int projectId, int userId) throws SQLException{

        if (ProjectService.canEdit(projectId, userId)) {
            Subtask subtask = new Subtask(name, description, java.sql.Date.valueOf(startDate), java.sql.Date.valueOf(endDate), hoursToComplete);
            subtask.setId(subtaskId);
            SubtaskRepository.updateSubtask(subtask);
        }

    }

    public static void deleteSubtask(int subtaskId, int projectId, int userId) throws SQLException {
        if (ProjectService.canEdit(projectId, userId)) {
            SubtaskRepository.deleteSubtask(subtaskId);
        }

    }

    public static void addEmployeeToSubtask(int subtaskId, int employeeId, int projectId, int userId) throws SQLException{
        if (ProjectService.canEdit(projectId, userId)) {
            SubtaskRepository.addEmployeeToSubtask(subtaskId, employeeId);
        }
    }

    public static void removeEmployeeFromSubtask(int subtaskId, int employeeId, int projectId, int userId) throws SQLException{
        if (ProjectService.canEdit(projectId, userId)) {
            SubtaskRepository.removeEmployeeFromSubtask(subtaskId, employeeId);
        }

    }

    public static void addSkillToSubtask(int subtaskId, int skillId, int projectId, int userId) throws SQLException{
        if (ProjectService.canEdit(projectId, userId)) {
            SubtaskRepository.addSkillToSubtask(subtaskId, skillId);
        }
    }

    public static void removeSkillFromSubtask(int subtaskId, int skillId, int projectId, int userId) throws SQLException{
        if (ProjectService.canEdit(projectId, userId)) {
            SubtaskRepository.removeSkillFromSubtask(subtaskId, skillId);
        }
    }
}
