package com.example.projecttool.models.project;

import com.example.projecttool.repositories.TaskRepository;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

// Matthias

public class Task {

    private int id;
    private String name;
    private String description;
    private String priority;
    private byte estimatedHoursPrDay;
    private int estimatedHoursTotal;
    private String start_time;
    private String end_time_calculated;
    private String end_time;
    private boolean countWeekends;
    private boolean endTimeCanBeCalculated = false;




    public Task(int id, String name, String description, String start_time, String end_time, String priority, byte estimatedHoursPrDay, boolean countWeekends) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.start_time = start_time;
        this.end_time = end_time;
        this.priority = priority;
        this.estimatedHoursPrDay = estimatedHoursPrDay;
        this.countWeekends = countWeekends;

    }

    public Task( String name, String description, String start_time, String end_time, String priority, byte estimatedHoursPrDay, boolean countWeekends) {
        this.name = name;
        this.description = description;
        this.start_time = start_time;
        this.end_time = end_time;
        this.priority = priority;
        this.estimatedHoursPrDay = estimatedHoursPrDay;
        this.countWeekends = countWeekends;

    }

    public boolean getCountWeekends() {
        return countWeekends;
    }

    public byte getEstimatedHoursPrDay() {
        return estimatedHoursPrDay;
    }

    public int getEstimatedHoursTotal() { return estimatedHoursTotal; }

    public String getName() {
        return name;
    }

    public String getTask() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getId() {
        return id;
    }

    public String getStart_time() {
        return start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public String getPriority() {
        return priority;
    }

    public String getEnd_time_calculated() {
        return end_time_calculated;
    }

    public boolean endTimeCanBeCalculated() {
        return endTimeCanBeCalculated;
    }

    public void setEndTimeCanBeCalculated(boolean endTimeCanBeCalculated) {
        this.endTimeCanBeCalculated = endTimeCanBeCalculated;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public void setEstimatedHoursPrDay(byte estimatedHoursPrDay) {
        this.estimatedHoursPrDay = estimatedHoursPrDay;
    }

    public void setEstimatedHoursTotal(int estimatedHoursTotal) {
        this.estimatedHoursTotal = estimatedHoursTotal;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public void setEnd_time_calculated(String end_time_calculated) {
        this.end_time_calculated = end_time_calculated;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public void setCountWeekends(boolean countWeekends) {
        this.countWeekends = countWeekends;
    }


}



