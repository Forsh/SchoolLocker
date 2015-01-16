package com.zfakgroup.israel.schoollocker;

import android.content.Context;

import com.google.gson.Gson;

/**
 * Created by mac on 14.01.15.
 *
 * Заглушка для класса группы. Наследует Search Result потому что у групп и курсов есть общие поля (???)
 *
 */
public class Group extends SearchResult{
    private Course[] courses;
    private User[] users;
    public Group(String name, String description, String imageURL, Course[] courses, User[] users,Context context) {
        super(name, description, imageURL, context);
        this.setCourses(courses);
        this.setUsers(users);
    }
    public Group()
    {}

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

    @Override
    public String toString() {
        Gson gson  = new Gson();
        return gson.toJson(this);
    }
}
