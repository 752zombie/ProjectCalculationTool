package com.example.projecttool.services;

import com.example.projecttool.models.project.Task;
import org.junit.jupiter.api.Test;


import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

// Matthias

class PrioritySorterServiceTest {


    @Test
    void prioritySorter() {

        // Arrange
        ArrayList<Task> taskList = new ArrayList<>();
        Task task = new Task(1, "Test", "Test1", "2014-05-05", "2014-06-06", "l", (byte) 1,true);
        Task task1 = new Task(1, "Test", "Test1", "2014-05-05", "2014-06-06","m", (byte) 1, false);
        Task task2 = new Task(1, "Test", "Test1", "2014-05-05", "2014-06-06","h", (byte) 1, true);
        Task task3 = new Task(1, "Test", "Test1", "2014-05-05", "2014-06-06","h", (byte) 1, true);
        Task task4 = new Task(1, "Test", "Test1", "2014-05-05", "2014-06-06", "h", (byte) 1, false);
        taskList.add(task);
        taskList.add(task1);
        taskList.add(task2);
        taskList.add(task3);
        taskList.add(task4);

        // Act
        taskList.sort((task5, task6) -> {
            String p1 = task5.getPriority();
            String p2 = task6.getPriority();

            if(p1 == null) return 1;
            if(p2 == null) return -1;
            if(p1.equals(p2)) return 0;
            if(p1.equals("h") && (p2.equals("m") || p2.equals("l")))
                return -1;
            if(p1.equals("m") && p2.equals("l"))
                return -1;
            return 1;
        });
        // Assert
        assertEquals(taskList.get(0).getPriority(), "h");
        assertEquals(taskList.get(3).getPriority(), "m");
        assertEquals(taskList.get(4).getPriority(), "l");

    }



}

