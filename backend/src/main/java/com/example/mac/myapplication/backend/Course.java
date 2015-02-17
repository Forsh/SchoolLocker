package com.example.mac.myapplication.backend;


import java.util.Date;

/**
 * Created by mac on 14.01.15.
 */
public class Course{

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

    private String[] files;
    private String[] folders;
    private String[] links;

    public Course(){

    }

    public Course(String name, String description, Date begin, Date end, String[] files, String[] folders, String[] links) {
        this.name = name;
        this.description = description;
        this.begin = begin;
        this.end = end;
        this.files = files;
        this.folders = folders;
        this.links = links;
    }

    public String[] getFiles() {
        return files;
    }

    public void setFiles(String[] files) {
        this.files = files;
    }

    public String[] getFolders() {
        return folders;
    }

    public void setFolders(String[] folders) {
        this.folders = folders;
    }

    public String[] getLinks() {
        return links;
    }

    public void setLinks(String[] links) {
        this.links = links;
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
