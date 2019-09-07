package com.yuanian.five.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class User {
    private String usercode;

    private String username;

    private String department;

    private Date hiredate;

    public User(String usercode, String username, String department, Date hiredate) {
        this.usercode = usercode;
        this.username = username;
        this.department = department;
        this.hiredate = hiredate;
    }

    public User() {
        super();
    }

    public String getUsercode() {
        return usercode;
    }

    public void setUsercode(String usercode) {
        this.usercode = usercode == null ? null : usercode.trim();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department == null ? null : department.trim();
    }

    public Date getHiredate() {
        return hiredate;
    }
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    public void setHiredate(Date hiredate) {
        this.hiredate = hiredate;
    }

    @Override
    public String toString() {
        return "User{" +
                "usercode='" + usercode + '\'' +
                ", username='" + username + '\'' +
                ", department='" + department + '\'' +
                ", hiredate=" + hiredate +
                '}';
    }
}