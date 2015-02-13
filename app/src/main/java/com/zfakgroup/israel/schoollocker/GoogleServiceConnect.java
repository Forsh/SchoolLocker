package com.zfakgroup.israel.schoollocker;

import com.example.mac.myapplication.backend.myApi.model.Course;
import com.example.mac.myapplication.backend.myApi.model.Group;
import com.example.mac.myapplication.backend.myApi.model.User;
import com.zfakgroup.israel.schoollocker.asynctasks.AsyncCallback;
import com.zfakgroup.israel.schoollocker.asynctasks.GetLoginAsync;

import java.util.HashMap;

/**
 * Created by mac on 25.01.15.
*/

public class GoogleServiceConnect implements IServiceConnect {
    public HashMap<String, AsyncCallback> listeners = new HashMap<>();

    public void setListener(String name, AsyncCallback listener){
        listeners.put(name, listener);
    }

    @Override
    public void login(String email, String password) {
        GetLoginAsync LoginAsync = new GetLoginAsync();
        LoginAsync.execute(listeners.get("login"), email, password);

    }

    @Override
    public boolean register(User newUser) {
        return false;
    }

    @Override
    public String[] getCountries() {
        return new String[0];
    }

    @Override
    public String[] getUniversities(String country) {
        return new String[0];
    }

    @Override
    public Group[] getGroupsBySearch(String GroupName) {
        return new Group[0];
    }

    @Override
    public void createGroup(Group newGroup) {

    }

    @Override
    public void createCourse(Course newCourse) {

    }

    @Override
    public Course[] getCoursesBySearch(String GroupName) {
        return new Course[0];
    }

    @Override
    public Course[] getCoursesOfMyGroup(String GroupName) {
        return new Course[0];
    }

    @Override
    public Course[] getMyCourses(String UserName) {
        return new Course[0];
    }

    @Override
    public User searchUser(String firstName, String lastName) {
        return null;
    }

    @Override
    public Group searchGroup(String Group) {
        return null;
    }

    @Override
    public Course searchCourse(String course) {
        return null;
    }
}