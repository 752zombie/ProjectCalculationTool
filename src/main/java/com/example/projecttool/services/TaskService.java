package com.example.projecttool.services;

import com.example.projecttool.models.project.Task;
import com.example.projecttool.repositories.ShareProjectRepository;
import com.example.projecttool.repositories.TaskRepository;
import com.example.projecttool.util.DueDateCalculator;
import com.example.projecttool.util.PrioritySorter;

import java.sql.SQLException;
import java.util.ArrayList;

public class TaskService {


    public static ArrayList<Task> getTasks(int projectId) throws SQLException {


        ArrayList<Task> allTasks = TaskRepository.getTasks(projectId);

        for (Task task : allTasks) {
            try {
                DueDateCalculator dueDateCalculator = new DueDateCalculator();
                String dueDate = dueDateCalculator.calculateDueDate(task.getEstimatedHoursPrDay(),
                        task.getEstimatedHoursTotal(), task.getStart_time(), task.getCountWeekends(),
                        getAmountOfEmployeesAssigned(task.getId()));
                task.setEnd_time_calculated(dueDate);
                task.setEndTimeCanBeCalculated(true);
            }

            catch (ArithmeticException e) {
                task.setEndTimeCanBeCalculated(false);
            }
        }

        PrioritySorter.sortTasksByPriority(allTasks);

        return allTasks;

    }

    public static void editTask(int taskId, String taskName, String description, String priority, String start_time, String end_time,
                         byte estimatedHoursDay, boolean countWeekends, int projectId, int userId) throws SQLException {
        boolean canEdit = ShareProjectRepository.getAccessLevel(projectId, userId).equals("rw");
        boolean isOwner = ProjectService.isOwner(projectId, userId);
        if (isOwner || canEdit) {
            TaskRepository.editTask(new Task(taskId, taskName, description, start_time, end_time, priority, estimatedHoursDay, countWeekends));
        }
    }

    public static void deleteTask(int taskId) throws SQLException {

        TaskRepository.deleteTask(taskId);
    }

    public static void createTask(int projectId, String name, String description, String priority, String start_time, String end_time,
                                  byte estimatedHoursDay, boolean countWeekends, int userId) throws SQLException {

        boolean canEdit = ShareProjectRepository.getAccessLevel(projectId, userId).equals("rw");
        boolean isOwner = ProjectService.isOwner(projectId, userId);

        if (isOwner || canEdit) {
            Task task = new Task(name, description, start_time, end_time, priority, estimatedHoursDay, countWeekends);
            TaskRepository.addRowToTask(projectId, task);
        }

    }

    public static int getAmountOfEmployeesAssigned(int taskId) throws SQLException{
        return TaskRepository.getTotalNumberOfEmployeesAssigned(taskId);
    }


    public static String getTaskName(Integer taskId) throws SQLException {

        return TaskRepository.getTaskName(taskId);
    }

}
