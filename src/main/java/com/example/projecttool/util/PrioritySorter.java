package com.example.projecttool.util;

import com.example.projecttool.models.project.Task;

import java.util.ArrayList;

// Matthias

public class PrioritySorter {

  public static void sortTasksByPriority(ArrayList<Task> taskList){

      // Sorts tasks after priority: High, medium, low
      taskList.sort((task1, task2) -> {
        String p1 = task1.getPriority();
        String p2 = task2.getPriority();

        if(p1 == null) return 1;
        if(p2 == null) return -1;
        if(p1.equals(p2)) return 0;
        if(p1.equals("h") && (p2.equals("m") || p2.equals("l")))
            return -1;
        if(p1.equals("m") && p2.equals("l"))
            return -1;
        return 1;
      });
  }

}
