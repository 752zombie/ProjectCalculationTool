package com.example.projecttool.services;

import com.example.projecttool.models.project.Employee;
import com.example.projecttool.models.project.Skill;
import com.example.projecttool.repositories.EmployeeRepository;
import com.example.projecttool.repositories.ProjectRepository;
import com.example.projecttool.repositories.SubtaskRepository;

import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeService {
    public static ArrayList<Employee> getAllEmployees(int userId) throws SQLException {
        return EmployeeRepository.getAllEmployees(userId);
    }

    public static ArrayList<Skill> getAllSkills(int userId) throws SQLException{
        return EmployeeRepository.getAllSkills(userId);
    }

    public static void changeEmployeeName(int employeeId, String name) throws SQLException{
        String[] firstNameLastName = parseFullName(name);
        if (firstNameLastName.length < 2) {
            EmployeeRepository.changeEmployeeName(employeeId, firstNameLastName[0], null);
        }

        else {
            EmployeeRepository.changeEmployeeName(employeeId, firstNameLastName[0], firstNameLastName[firstNameLastName.length - 1]);
        }
    }

    public static void deleteEmployee(int employeeId) throws SQLException{
        EmployeeRepository.deleteEmployee(employeeId);
    }

    public static void createEmployee(String name, int userId) throws SQLException{
        String[] firstNameLastName = parseFullName(name);
        if (firstNameLastName.length < 2) {
            EmployeeRepository.createNewEmployee(firstNameLastName[0], null, userId);
        }

        else {
            EmployeeRepository.createNewEmployee(firstNameLastName[0], firstNameLastName[firstNameLastName.length - 1], userId);
        }
    }

    public static void createNewSkill(int userId, String skillName) throws SQLException{
        EmployeeRepository.createNewSkill(userId, skillName);
    }

    public static void deleteSkill(int skillId) throws SQLException{
        EmployeeRepository.deleteSkill(skillId);
    }

    public static void addSkillToEmployee(int employeeId, int skillId) throws SQLException{
        EmployeeRepository.addSkillToEmployee(employeeId, skillId);
    }

    public static void removeSkillFromEmployee(int employeeId, int skillId) throws SQLException{
        EmployeeRepository.removeSkillFromEmployee(employeeId, skillId);
    }

    private static String[] parseFullName(String fullName) {
        return fullName.split("\\s");
    }

    public static ArrayList<Employee> getAllEmployees(int userId, int projectId) throws SQLException {
        ArrayList<Employee> employees = new ArrayList<>();
        int ownerId = ProjectRepository.getOwnerId(projectId);
        if (ProjectService.hasAccess(projectId, userId)) {
            employees = EmployeeRepository.getAllEmployees(ownerId);
        }

        return employees;
    }
}
