/*



   For step-by-step instructions on connecting your Android application to this backend module,
   see "App Engine Java Endpoints Module" template documentation at
   https://github.com/GoogleCloudPlatform/gradle-appengine-templates/tree/master/HelloEndpoints
*/

package com.example.mac.myapplication.backend;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;

import java.sql.ResultSet;
import java.sql.Statement;
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

    /**
     * A simple endpoint method that takes a name and says Hi back
     */
    @ApiMethod(name = "sayHi")
    public MyBean sayHi(@Named("name") String name) {
        MyBean response = new MyBean();
        String url;
        response.setData("BEFORETRY");
        try {
            if (SystemProperty.environment.value() ==
                    SystemProperty.Environment.Value.Production) {
            // Connecting from App Engine.
            // Load the class that provides the "jdbc:google:mysql://"
            // prefix.
                Class.forName("com.mysql.jdbc.GoogleDriver");

                url =
                        "jdbc:google:mysql://golden-tempest-803:forshtata?user=root";
            } else {
                // Connecting from an external network.
                Class.forName("com.mysql.jdbc.Driver");
                url = "jdbc:mysql://173.194.254.146:3306?user=root";
            }

            Connection conn = DriverManager.getConnection(url);
            ResultSet resultSet = conn.createStatement().executeQuery(
                    "SELECT 1 + 1");

            if (resultSet.next())
                response.setData(resultSet.getString(0));
            else
                response.setData("EMPTY");


        } catch (Exception e) {
            e.printStackTrace();
            response.setData(e.toString());
        }
        return response;
    }

}
