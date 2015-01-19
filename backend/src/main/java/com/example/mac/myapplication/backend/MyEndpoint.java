package com.example.mac.myapplication.backend;
/*



   For step-by-step instructions on connecting your Android application to this backend module,
   see "App Engine Java Endpoints Module" template documentation at
   https://github.com/GoogleCloudPlatform/gradle-appengine-templates/tree/master/HelloEndpoints
*/


import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;

import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.inject.Named;


import com.google.appengine.api.utils.SystemProperty;

/**
 * An endpoint class we are exposing
 */


@Api(name = "myApi", version = "v1", namespace = @ApiNamespace(ownerDomain = "backend.myapplication.mac.example.com", ownerName = "backend.myapplication.mac.example.com", packagePath = ""))
public class MyEndpoint {
    private static final String GET_GROUP_QUERY = "SELECT Name, Description FROM GROUPS WHERE Id = ";

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
    public Group getGroup(@Named("id") String id){
        Group found = new Group();
        try {
            ResultSet resultSet = doQuery(GET_GROUP_QUERY+id);
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
}
