package com.example.projecttool.repositories;

import com.example.projecttool.models.project.Subtask;
import com.example.projecttool.models.project.Task;

import java.sql.*;
import java.util.ArrayList;

public class TaskRepository {


    public static ArrayList<Task> getTasks(int projectId) throws SQLException {

        Connection connection = DatabaseConnection.getConnection();
        ArrayList<Task> taskList = new ArrayList<>();

        PreparedStatement statement = connection.prepareStatement("SELECT * FROM tasks WHERE project_id = ?");
        statement.setInt(1, projectId);
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String project_name = resultSet.getString("task_name");
            String project_description = resultSet.getString("task_description");
            String priority = resultSet.getString("priority");
            String start_time = resultSet.getString("start_time");
            String end_time = resultSet.getString("end_time");
            int estimatedHours = TaskRepository.getTotalHoursToComplete(id);
            byte estimatedHoursPrDay = resultSet.getByte("estimated_hours_day");
            boolean countWeekends = resultSet.getBoolean("count_weekends");

            Task task = new Task(id, project_name, project_description, start_time, end_time, priority, estimatedHoursPrDay, countWeekends);
            task.setEstimatedHoursTotal(estimatedHours);
            taskList.add(task);
        }

        return taskList;
    }


    public static void editTask(Task task) throws SQLException {
        Connection connection = DatabaseConnection.getConnection();

        PreparedStatement statement = connection.prepareStatement("UPDATE tasks SET task_name = ?, " +
                "task_description = ?, start_time = ?, end_time = ?, priority = ?, " +
                "estimated_hours_day = ?, count_weekends = ? WHERE id = ?");

        statement.setString(1, task.getName());
        statement.setString(2, task.getDescription());
        statement.setDate(3, java.sql.Date.valueOf(task.getStart_time()));
        statement.setDate(4, java.sql.Date.valueOf(task.getEnd_time()));
        statement.setString(5, task.getPriority());
        statement.setByte(6, task.getEstimatedHoursPrDay());
        statement.setBoolean(7, task.getCountWeekends());
        statement.setInt(8, task.getId());

        statement.execute();

    }


    public static void addRowToTask(int projectId, Task task) throws SQLException {


        Connection connection = DatabaseConnection.getConnection();


        PreparedStatement statement = connection.prepareStatement("INSERT INTO tasks(project_id, " +
                "task_name, task_description, start_time, end_time, priority, " +
                "estimated_hours_day, count_weekends) values (?, ?, ?, ?, ?, ?, ?, ?)");

        statement.setInt(1, projectId);
        statement.setString(2, task.getName());
        statement.setString(3, task.getDescription());
        statement.setDate(4, java.sql.Date.valueOf(task.getStart_time()));
        statement.setDate(5, java.sql.Date.valueOf(task.getEnd_time()));
        statement.setString(6, task.getPriority());
        statement.setByte(7, task.getEstimatedHoursPrDay());
        statement.setBoolean(8, task.getCountWeekends());

        statement.execute();

    }


    public static ArrayList<Subtask> getRelatedSubtasks(int taskId) {
        Connection connection = DatabaseConnection.getConnection();
        ArrayList<Subtask> subtasks = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM subtasks WHERE task = ? ORDER BY id");
            statement.setInt(1, taskId);
            ResultSet resultSet = statement.executeQuery();


            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("subtask_name");
                String description = resultSet.getString("subtask_description");
                Date startTime = resultSet.getDate("start_time");
                Date endTime = resultSet.getDate("end_time");
                int hoursToComplete = resultSet.getInt("hours_to_complete");

                Subtask subtask = new Subtask(id, name, description, startTime, endTime,
                        SubtaskRepository.getAssignedEmployees(id), SubtaskRepository.getRequiredSkills(id), hoursToComplete);
                subtasks.add(subtask);
            }

        } catch (SQLException e) {
            System.out.println("Error could not find any subtasks for that task");
        }

        return subtasks;
    }


    public static void deleteTask(int taskId) throws SQLException {

        Connection connection = DatabaseConnection.getConnection();

        PreparedStatement statement = connection.prepareStatement("DELETE FROM tasks WHERE id = ?");
        statement.setInt(1, taskId);
        statement.execute();
    }

    public static int getTotalNumberOfEmployeesAssigned(int taskId) throws SQLException{
        Connection connection = DatabaseConnection.getConnection();

        PreparedStatement statement = connection.prepareStatement("SELECT COUNT(*) FROM subtasks " +
                "INNER JOIN emp_subtask " +
                "ON subtasks.id = emp_subtask.subtask_id " +
                "WHERE task = ?");
        statement.setInt(1, taskId);
        ResultSet resultSet = statement.executeQuery();
        resultSet.next();
        return resultSet.getInt(1);

    }

    public static int getTotalHoursToComplete(int taskId) throws SQLException{
        Connection connection = DatabaseConnection.getConnection();

        PreparedStatement statement = connection.prepareStatement("SELECT SUM(hours_to_complete) FROM subtasks WHERE task = ?");
        statement.setInt(1, taskId);
        ResultSet resultSet = statement.executeQuery();
        resultSet.next();

        return resultSet.getInt(1);
    }

    public static String getTaskName(Integer taskId) throws SQLException {

        Connection connection = DatabaseConnection.getConnection();

        PreparedStatement statement = connection.prepareStatement("SELECT task_name FROM tasks WHERE id = ?");
        statement.setInt(1, taskId);
        ResultSet resultSet = statement.executeQuery();
        resultSet.next();

        return resultSet.getString(1);

    }
}



