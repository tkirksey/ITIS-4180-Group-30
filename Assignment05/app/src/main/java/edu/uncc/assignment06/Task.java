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
    private long date;

    /**
     * The priority of the task. the lowest being the highest
     */
    private int priority;

    public Task(){
        this.name = "";
        this.priority = -1;
        this.date = 0;
    }

    public Task(String name, long date, int priority){
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

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

}
