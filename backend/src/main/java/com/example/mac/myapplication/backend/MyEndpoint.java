package com.example.mac.myapplication.backend;
/*



   For step-by-step instructions on connecting your Android application to this backend module,
   see "App Engine Java Endpoints Module" template documentation at
   https://github.com/GoogleCloudPlatform/gradle-appengine-templates/tree/master/HelloEndpoints
*/


import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;


import com.google.appengine.api.utils.SystemProperty;

/**
 * An endpoint class we are exposing
 */


@Api(name = "myApi", version = "v1", namespace = @ApiNamespace(ownerDomain = "backend.myapplication.mac.example.com", ownerName = "backend.myapplication.mac.example.com", packagePath = ""))
public class MyEndpoint {
    private static final String GET_GROUP_QUERY = "SELECT Name, Description FROM GROUPS WHERE Id = ";
    private static final String LIST_GROUPS_QUERY =
            "SELECT * FROM GROUPS " +
            "JOIN " +
            "USERSINGROUPS " +
            "ON " +
            "USERSINGROUPS.GroupId = GROUPS.Id " +
            "having " +
            "USERSINGROUPS.UserId =";
    private static final String GET_COURSE_QUERY = "SELECT Name, Description FROM COURSES WHERE Id = " ;
    private static final String LIST_COURSES_QUERY =
            "SELECT * FROM COURSES "; /*+
            "JOIN " +
            "USERSINCOURSES " +
            "ON " +
            "USERSINCOURSES.CourseId = COURSES.Id " +
            "having " +
            "USERSINCOURSES.UserId = ";*/
    private static final String LIST_COUNTRIES_QUERY = "SELECT * FROM COUNTRIES";
    private static final String LIST_UNIVERSITIES_QUERY = "SELECT * FROM UNIVERSITIES";
    private static final String GET_USER = "SELECT * FROM USERS";

    //region A simple endpoint method that takes a name and says Hi back
/*
     * A simple endpoint method that takes a name and says Hi back
    @ApiMethod(name = "sayHi")
    public MyBean sayHi(@Named("name") String name) {
        MyBean response = new MyBean();
        String url;
        response.setData("BEFORETRY");

        return response;
    }
    */
    //endregion

    public MyEndpoint() {
    }

    @ApiMethod(name = "getGroup")
    public Group getGroup(@Named("id") String group_id){
        Group found = new Group();
        try {
            ResultSet resultSet = doQuery(GET_GROUP_QUERY+group_id);
            if (resultSet.next()) {
                found.setName(
                        resultSet.getString("Name"));
                found.setDescription(
                        resultSet.getString("Description")
                );
            }


        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            found.setDescription(e.toString());
        } catch (SQLException e) {
            e.printStackTrace();
            found.setDescription(e.toString());
        }

        return found;
    }


    @ApiMethod(name = "listGroups", path = "listGroups")
    public List<Group> listGroups(@Named("id") String id){
        if (id == ""){
            id = "USERSINGROUPS.UserId";
        }
        ArrayList<Group> groupArrayList = new ArrayList<>();
        try {
            ResultSet resultSet = doQuery(LIST_GROUPS_QUERY+id);
            while (resultSet.next()) {
                Group found = new Group();
                found.setName(
                        resultSet.getString("Name"));
                found.setDescription(
                        resultSet.getString("Description")
                );
                groupArrayList.add(found);
            }

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return groupArrayList;
    }


    @ApiMethod(name = "getCourse", path = "getCourse")
    public Course getCourse(@Named("id") String course_id){
        Course found = new Course();
        try {
            ResultSet resultSet = doQuery(GET_COURSE_QUERY+course_id);
            if (resultSet.next()) {
                found.setName(
                        resultSet.getString("Name"));
                found.setDescription(
                        resultSet.getString("Description")
                );
            }


        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            found.setDescription(e.toString());
        } catch (SQLException e) {
            e.printStackTrace();
            found.setDescription(e.toString());
        }

        return found;
    }


    @ApiMethod(name = "listCourses", path = "listCourses")
    public ArrayList<Course> listCourses(@Named("id") String id){
//        if (id == ""){
//            id = "USERSINCOURSES.UserId";
//        }
        ArrayList<Course> groupArrayList = new ArrayList<>();
        try {
            ResultSet resultSet = doQuery(LIST_COURSES_QUERY+id);
            while (resultSet.next()) {
                Course found = new Course();
                found.setName(
                        resultSet.getString("Name"));
                found.setDescription(
                        resultSet.getString("Description")
                );
                groupArrayList.add(found);
            }

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return groupArrayList;
    }


    @ApiMethod(name = "listCountries", path = "listCountries")
    public ArrayList<Country> listCountries(){
        ArrayList<Country> groupArrayList = new ArrayList<>();
        try {
            ResultSet resultSet = doQuery(LIST_COUNTRIES_QUERY);
            while (resultSet.next()) {
                Country found = new Country();
                found.setName(
                        resultSet.getString("Name"));
                groupArrayList.add(found);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return groupArrayList;
    }


    @ApiMethod(name = "listUniversities", path = "listUniversities")
    public ArrayList<University> listUniversities(){
        ArrayList<University> groupArrayList = new ArrayList<>();
        try {
            ResultSet resultSet = doQuery(LIST_UNIVERSITIES_QUERY);
            while (resultSet.next()) {
                University found = new University();
                found.setName(
                        resultSet.getString("Name"));
                groupArrayList.add(found);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return groupArrayList;
    }


    private ResultSet doQuery (String query) throws ClassNotFoundException, SQLException {
        ResultSet resultSet;
        String url;
            if (SystemProperty.environment.value() ==
                    SystemProperty.Environment.Value.Production) {
                // Connecting from App Engine.
                // Load the class that provides the "jdbc:google:mysql://"
                // prefix.
                Class.forName("com.mysql.jdbc.GoogleDriver");

                url =
                        "jdbc:google:mysql://golden-tempest-803:forshtata/MyDatabase?user=root";
            } else {
                // Connecting from an external network.
                Class.forName("com.mysql.jdbc.Driver");
                url = "jdbc:mysql://173.194.254.146:3306?user=root";
            }

            Connection conn = DriverManager.getConnection(url);
            resultSet= conn.createStatement().executeQuery(query);

        return resultSet;
    }

    @ApiMethod(name = "createCourse", path = "createCourse", httpMethod = "POST")
    public Course createCourse(Course course){

        String url;
        if (SystemProperty.environment.value() ==
                SystemProperty.Environment.Value.Production) {
            // Connecting from App Engine.
            // Load the class that provides the "jdbc:google:mysql://"
            // prefix.
            try {
                Class.forName("com.mysql.jdbc.GoogleDriver");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            url =
                    "jdbc:google:mysql://golden-tempest-803:forshtata/MyDatabase?user=root";
        } else {
            // Connecting from an external network.
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            url = "jdbc:mysql://173.194.254.146:3306?user=root";
        }
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //INSERT INTO COURSES(Name, Description) VALUES("Nuclear physics", "Harvard University course leads you through newest nuclear phisics theories");
        String query = "INSERT INTO COURSES(Name, Description) VALUES( ? , ? )";
        if (conn != null) {
            try {
                PreparedStatement ps = conn.prepareStatement(query);
                ps.setString(1, course.getName());
                ps.setString(2, course.getDescription());
                int success = 2;
                success = ps.executeUpdate();
                if (success == 1) {
                    Course course1 = new Course();
                    course1.setDescription("Success!");
                    return course1;
                } else if (success == 0) {
                    Course course1 = new Course();
                    course1.setDescription("Failed!");
                    return course1;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return new Course();
    }

    @ApiMethod(name = "login", path = "login")
    public User login(@Named("email") String email, @Named("password") String password){
        User found = new User();
        try {
            ResultSet resultSet = doQuery(GET_USER);
            if (resultSet.next()) {
                found.setName(
                        resultSet.getString("Name")
                );
                found.setId(
                        resultSet.getInt("Id")
                );
                found.setEmail(
                        resultSet.getString("Email")
                );
            }


        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            found.setName(e.toString());
        } catch (SQLException e) {
            e.printStackTrace();
            found.setName(e.toString());
        }

        return found;
    }
}
