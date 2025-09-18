package com.example.assignment4;

import java.io.Serializable;

public class User implements Serializable {

    private String name;
    private String email;
    private String role;

    public User(String name, String email, String role){
        this.email = email;
        this.name = name;
        this.role = role;
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

}
