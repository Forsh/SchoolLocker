package com.zfakgroup.israel.schoollocker;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;


/**
 * Created by mozartenok on 15.01.15.
 */
public class ConnectToLocalDB implements IServiceConnect {


    public ConnectToLocalDB() {

    //connect to local DB
   //SQLiteDatabase db = dbHelper.getWritableDatabase();

    }

    @Override
    public int login(String email, String password) {

        Log.d("MESSAGE_FROM_CONNECTDB",email);
        Log.d("MESSAGE_FROM_CONNECTDB",password);





        return 0;
    }


    @Override
    public boolean register(User newUser) {

        //create the new user in Database: email, password, firstname,lastname, country,university





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
    public Group[] getGroupsInUniverstity(String UniversityName) {
        return new Group[0];
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
        //create course: group, name,description, startday, endday
    }

    @Override
    public Course[] getCoursesInUniverstity(String UniversityName) {
        return new Course[0];
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
    public Course searchCourse(String course) {
        return null;
    }

    @Override
    public Group searchGroup(String Group) {
        return null;
    }
}
