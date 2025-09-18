package com.example.assignment03;

import java.io.Serializable;

public class User implements Serializable {

    final static String KEY = "USER_KEY";

    private String name;
    private String email;
    private String role;

    public User(String name, String email, String role){
        this.name = name;
        this.email = email;
        this.role = role;
    }

    public User(User usr){
        this.name = usr.getName();
        this.email = usr.getEmail();
        this.role = usr.getRole();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String toString(){

        return "User:{.name: " + name + ", .email: " + email + ", .role: " + role + "}";

    }
}
