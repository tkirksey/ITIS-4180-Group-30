package edu.uncc.assignment06;

import java.io.Serializable;

public class Task implements Serializable {

    /**
     * Name of the task
     */
    private String name;

    /**
     * Time in milliseconds since Epoch
     */
    private String date;

    /**
     * The priority of the task. the lowest being the highest
     */
    private String priority;

    public Task(){
        this.name = "";
        this.priority = "";
        this.date = "";
    }

    public Task(String name, String date, String priority){
        this.name = name;
        this.date = date;
        this.priority = priority;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

}
