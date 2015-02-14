package com.example.mac.myapplication.backend;

import com.google.appengine.api.utils.SystemProperty;
import com.google.appengine.tools.cloudstorage.GcsFileOptions;
import com.google.appengine.tools.cloudstorage.GcsFilename;
import com.google.appengine.tools.cloudstorage.GcsOutputChannel;
import com.google.appengine.tools.cloudstorage.GcsService;
import com.google.appengine.tools.cloudstorage.GcsServiceFactory;
import com.google.appengine.tools.cloudstorage.RetryParams;

import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.channels.Channels;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        resp.setContentType("text/plain");
        resp.getWriter().println("Please use the form to POST to this url");
    }


    private static final Logger log = Logger.getLogger(MyServlet.class.getName());

    private final GcsService gcsService = GcsServiceFactory.createGcsService(new RetryParams.Builder()
            .initialRetryDelayMillis(10)
            .retryMaxAttempts(10)
            .totalRetryPeriodMillis(15000)
            .build());

    private String bucketName = "forshtatcloudstoragebucket";

    /**Used below to determine the size of chucks to read in. Should be > 1kb and < 10MB */
    private static final int BUFFER_SIZE = 2 * 1024 * 1024;

    @SuppressWarnings("unchecked")
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        String sctype = null, sname = null;
        ServletFileUpload upload;
        FileItemIterator iterator;
        FileItemStream item;
        InputStream stream = null;

        try {
            upload = new ServletFileUpload();
            res.setContentType("text/plain");

            iterator = upload.getItemIterator(req);
            while (iterator.hasNext()) {
                item = iterator.next();
                stream = item.openStream();

                if (item.isFormField()) {
                    log.warning("Got a form field: " + item.getFieldName());
                } else {
                    log.warning("Got an uploaded file: " + item.getFieldName() +
                            ", name = " + item.getName());

                    //sfieldname = item.getFieldName();
                    sname = item.getName();

                    sctype = item.getContentType();
                    if (sctype == null){
                        sctype  = "text/plain";
                    }
                    GcsFilename gcsfileName = new GcsFilename(bucketName, sname);

                    GcsFileOptions options = new GcsFileOptions.Builder()
                            .acl("public-read").mimeType(sctype).build();

                    GcsOutputChannel outputChannel =
                            gcsService.createOrReplace(gcsfileName, options);

                    copy(stream, Channels.newOutputStream(outputChannel));
                    Content created = new Content(0, sname, "https://http://storage.googleapis.com/forshtatcloudstoragebucket/"+sname, 0);
                    createContent(created);
                    res.sendRedirect("/");
                }
            }
        } catch (Exception ex) {
            throw new ServletException(ex);
        }

    }

    public void createContent(Content course) {

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
        String query = "INSERT INTO CONTENTS(Name, URL, FileFolderLink) VALUES( ? , ? , ?)";
        if (conn != null) {
            try {
                PreparedStatement ps = conn.prepareStatement(query);
                ps.setString(1, course.getName());
                ps.setString(2, course.getURL());
                ps.setInt(3, course.getType());
                int success = 2;
                success = ps.executeUpdate();
//                if (success == 1) {
//                    Course course1 = new Course();
//                    course1.setDescription("Success!");
//                    return;// course1;
//                } else if (success == 0) {
//                    Course course1 = new Course();
//                    course1.setDescription("Failed!");
//                    return;// course1;
//                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return;// new Course();
    }


    private void copy(InputStream input, OutputStream output) throws IOException {
        try {
            byte[] buffer = new byte[BUFFER_SIZE];
            int bytesRead = input.read(buffer);
            while (bytesRead != -1) {
                output.write(buffer, 0, bytesRead);
                bytesRead = input.read(buffer);
            }
        } finally {
            input.close();
            output.close();
        }
    }
}





///*
//   For step-by-step instructions on connecting your Android application to this backend module,
//   see "App Engine Java Servlet Module" template documentation at
//   https://github.com/GoogleCloudPlatform/gradle-appengine-templates/tree/master/HelloWorld
//*/
//
//package com.example.mac.myapplication.backend;
//
//import java.io.ByteArrayInputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.OutputStream;
//import java.nio.channels.Channels;
//
//import javax.servlet.http.*;
//
//public class MyServlet extends HttpServlet {
//
//
//    @Override
//    public void doPost(HttpServletRequest req, HttpServletResponse resp)
//            throws IOException {
//        String name = req.getParameter("name");
//        resp.setContentType("text/plain");
//        if (name == null) {
//            resp.getWriter().println("Please enter a name");
//        }
//        resp.getWriter().println("Hello " + name);
//    }
//}
////
////private static int BUFFER_SIZE = 2 * 1024 * 1024;
////
////    private void copy(InputStream input, OutputStream output) throws IOException {
////        try {
////            byte[] buffer = new byte[BUFFER_SIZE];
////            int bytesRead = input.read(buffer);
////            while (bytesRead != -1) {
////                output.write(buffer, 0, bytesRead);
////                bytesRead = input.read(buffer);
////            }
////        } finally {
////            input.close();
////            output.close();
////        }
////    }
////    private GcsFilename getFileName(String req){
////        String[] splits = req.split("/", 4);
//////        if (!splits[0].equals("") || !splits[1].equals("gcs")) {
//////            throw new IllegalArgumentException("The URL is not formed as expected. " +
//////                    "Expecting /gcs/<bucket>/<object>");
//////        }
////        return new GcsFilename(splits[2], splits[3]);
////    }
//////
//////    @ApiMethod(name = "write", path = "write", httpMethod = "POST")
////    public User writeToBucket() {
////
////        GcsService gcsService = GcsServiceFactory.createGcsService(new RetryParams.Builder()
////                .initialRetryDelayMillis(10)
////                .retryMaxAttempts(10)
////                .totalRetryPeriodMillis(15000)
////                .build());
////        User u = new User();
////        try {
////            GcsFileOptions options = new GcsFileOptions.Builder()
////                    .acl("public-read").build();
////            GcsOutputChannel outputChannel =
////                    gcsService.createOrReplace(new GcsFilename("forshtatcloudstoragebucket", "folder/hi"),
////                            options);
////            if (outputChannel == null) {
////                u.setName("NO OUTPUT CHANEL");
////                return u;
////            }
////            InputStream io = new ByteArrayInputStream("Helloworld".getBytes());
////            if (io == null) {
////                u.setName("NO INPUT CHANEL");
////                return u;
////            }
////            copy(io, Channels.newOutputStream(outputChannel));
////        } catch (Exception e) {
////            u.setName(e.getMessage());
////        }
////        return u;
////    }
