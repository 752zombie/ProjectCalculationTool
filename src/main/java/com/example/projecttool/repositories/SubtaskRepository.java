package com.example.projecttool.repositories;

import com.example.projecttool.models.project.Employee;
import com.example.projecttool.models.project.Skill;
import com.example.projecttool.models.project.Subtask;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SubtaskRepository {

    public static void addEmployeeToSubtask(int subtaskId, int employeeId) throws SQLException {
        Connection connection = DatabaseConnection.getConnection();

        PreparedStatement statement = connection.prepareStatement("INSERT INTO emp_subtask(emp_id, subtask_id) values (?, ?)");
        statement.setInt(1, employeeId);
        statement.setInt(2, subtaskId);
        statement.execute();

    }

    public static void removeEmployeeFromSubtask(int subtaskId, int employeeId) throws SQLException{
        Connection connection = DatabaseConnection.getConnection();

        PreparedStatement statement = connection.prepareStatement("DELETE FROM emp_subtask WHERE emp_id = ? AND subtask_id = ?");
        statement.setInt(1, employeeId);
        statement.setInt(2, subtaskId);
        statement.execute();

    }

    public static void addSkillToSubtask(int subtaskId, int skillId) throws SQLException {
        Connection connection = DatabaseConnection.getConnection();

        PreparedStatement statement = connection.prepareStatement("INSERT INTO subtask_skill(subtask_id, skill_id) values (?, ?)");
        statement.setInt(1, subtaskId);
        statement.setInt(2, skillId);
        statement.execute();

    }

    public static void removeSkillFromSubtask(int subtaskId, int skillId) throws SQLException{
        Connection connection = DatabaseConnection.getConnection();

        PreparedStatement statement = connection.prepareStatement("DELETE FROM subtask_skill WHERE subtask_id = ? AND skill_id = ?");
        statement.setInt(1, subtaskId);
        statement.setInt(2, skillId);
        statement.execute();

    }



    public static ArrayList<Employee> getAssignedEmployees(int subtaskId) {
        Connection connection = DatabaseConnection.getConnection();
        ArrayList<Employee> employees = new ArrayList<>();

        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM emp_subtask " +
                    "INNER JOIN employees " +
                    "ON emp_subtask.emp_id = employees.emp_id " +
                    "WHERE emp_subtask.subtask_id = ?");
            statement.setInt(1, subtaskId);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int employeeId = resultSet.getInt("emp_id");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                ArrayList<Skill> skills = getEmployeeSkills(employeeId);

                employees.add(new Employee(employeeId, firstName, lastName, skills));

            }

        }

        catch (SQLException e) {
            System.out.println("Error getting assigned employees");
        }

        return employees;
    }

    private static ArrayList<Skill> getEmployeeSkills(int employeeID) {
        Connection connection = DatabaseConnection.getConnection();
        ArrayList<Skill> skills = new ArrayList<>();

        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM emp_skill " +
                    "INNER JOIN skills " +
                    "ON emp_skill.skill_id = skills.skill_id " +
                    "WHERE emp_id = ?");
            statement.setInt(1, employeeID);
            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next()) {
                int skillId = resultSet.getInt("skill_id");
                String skillName = resultSet.getString("skill_name");
                skills.add(new Skill(skillName, skillId));
            }
        }

        catch (SQLException e) {
            e.printStackTrace();
        }

        return skills;
    }

    public static ArrayList<Skill> getRequiredSkills(int subtaskId) {
        Connection connection = DatabaseConnection.getConnection();
        ArrayList<Skill> skills = new ArrayList<>();

        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM subtask_skill " +
                    "INNER JOIN skills " +
                    "ON subtask_skill.skill_id = skills.skill_id " +
                    "WHERE subtask_id = ?");
            statement.setInt(1, subtaskId);
            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next()) {
                int skillId = resultSet.getInt("skill_id");
                String skillName = resultSet.getString("skill_name");
                skills.add(new Skill(skillName, skillId));
            }
        }

        catch (SQLException e) {
            e.printStackTrace();
        }

        return skills;
    }

    public static void addNewSubtaskToTask(int taskId, Subtask subtask) throws SQLException{
        Connection connection = DatabaseConnection.getConnection();

        PreparedStatement statement = connection.prepareStatement("INSERT INTO subtasks(task, subtask_name, subtask_description, start_time, " +
                "end_time, hours_to_complete) VALUES (?, ?, ?, ?, ?, ?)");
        statement.setInt(1, taskId);
        statement.setString(2, subtask.getName());
        statement.setString(3, subtask.getDescription());
        statement.setDate(4, subtask.getStartTime());
        statement.setDate(5, subtask.getEndTime());
        statement.setInt(6, subtask.getHoursToComplete());
        statement.execute();
    }


    public static void deleteSubtask(int subtaskId) throws SQLException{
        Connection connection = DatabaseConnection.getConnection();

        PreparedStatement statement = connection.prepareStatement("DELETE FROM subtasks WHERE id = ?");
        statement.setInt(1, subtaskId);
        statement.execute();
    }

    public static void updateSubtask(Subtask subtask) throws SQLException {
        Connection connection = DatabaseConnection.getConnection();

        PreparedStatement statement = connection.prepareStatement("UPDATE subtasks SET subtask_name = ?, subtask_description = ?, " +
                "start_time = ?, end_time = ?, hours_to_complete = ? WHERE id = ?");

        statement.setString(1, subtask.getName());
        statement.setString(2, subtask.getDescription());
        statement.setDate(3, subtask.getStartTime());
        statement.setDate(4, subtask.getEndTime());
        statement.setInt(5, subtask.getHoursToComplete());
        statement.setInt(6, subtask.getId());
        statement.execute();
    }

    public static ArrayList<Employee> getAllEmployees(int userId) throws SQLException{
        Connection connection = DatabaseConnection.getConnection();
        ArrayList<Employee> employees = new ArrayList<>();

        PreparedStatement statement = connection.prepareStatement("SELECT * FROM employees WHERE user_id = ?");
        statement.setInt(1, userId);
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            int empId = resultSet.getInt("emp_id");
            String firstName = resultSet.getString("first_name");
            String lastName = resultSet.getString("last_name");
            ArrayList<Skill> skills = getEmployeeSkills(empId);
            employees.add(new Employee(empId, firstName, lastName, skills));
        }

        return employees;
    }


}
