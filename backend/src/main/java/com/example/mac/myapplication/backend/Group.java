package com.example.mac.myapplication.backend;

import java.util.Date;

/**
 * Created by mac on 14.01.15.
 *
 * Заглушка для класса группы.
 *
 */

public class Group{

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    private int id;

    private String name;
    private String description;
    private Date begin;
    private Date end;
    private Course[] courses;
    private User[] users;

    public Group()
    {}

    public Group(String name, String description, Date begin, Date end, User[] users, int id) {
        this.name = name;
        this.description = description;
        this.begin = begin;
        this.end = end;
        this.id = id;
        this.courses = courses;
        this.users = users;
    }

    public Course[] getCourses() {
        return courses;
    }

    public void setCourses(Course[] courses) {
        this.courses = courses;
    }

    public User[] getUsers() {
        return users;
    }

    public void setUsers(User[] users) {
        this.users = users;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getBegin() {
        return begin;
    }

    public void setBegin(Date begin) {
        this.begin = begin;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }
}
