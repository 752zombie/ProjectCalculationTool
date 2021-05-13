package com.example.projecttool.services;

import com.example.projecttool.models.project.Task;
import com.example.projecttool.repositories.TaskRepository;

import java.sql.SQLException;
import java.util.ArrayList;

public class TaskService {

    public ArrayList<Task> getTasks(Integer projectId) throws SQLException {


        ArrayList<Task> allTasks = TaskRepository.getTasks(projectId);

        PrioritySorterService.sortTasksByPriority(allTasks);

        return allTasks;

    }


    public void editTask(int taskId, String taskName, String description, String priority, String start_time,
                         int estimatedHoursTotal, int estimatedHoursDay) throws SQLException {

        DueDateCalculator dueDate = new DueDateCalculator();
        String end_time = dueDate.dueDate(estimatedHoursDay, estimatedHoursTotal, start_time);

       TaskRepository.editTask(taskId, taskName, description, priority, start_time, end_time, estimatedHoursTotal, estimatedHoursDay);
    }

    public void deleteTask(int taskId) throws SQLException {

        TaskRepository.deleteTask(taskId);
    }

    public void addRowToTask(int projectId, String name, String description, String priority, String start_time,
                             int estimatedHoursDay, int estimatedHoursTotal) throws SQLException {



        DueDateCalculator dueDate = new DueDateCalculator();
        String end_time = dueDate.dueDate(estimatedHoursDay, estimatedHoursTotal, start_time);

        TaskRepository.addRowToTask(projectId, name, description, priority, start_time, end_time, estimatedHoursTotal, estimatedHoursDay);
    }
}
