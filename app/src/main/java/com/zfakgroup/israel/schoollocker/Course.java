package com.zfakgroup.israel.schoollocker;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Arrays;

/**
 * Created by mac on 14.01.15.
 */
public class Course extends SearchResult{
    private String[] files;
    private String[] folders;
    private String[] links;

    public Course(){

    }

    public Course(String name, String description, String imageURL, String[] files, String[] folders, String[] links, Context context) {
        super(name, description, imageURL, context);
        this.setFolders(folders);
        this.setFiles(files);
        this.setLinks(links);
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

    @Override
    public String toString() {
        Gson gson  = new Gson();
        return gson.toJson(this);
    }
}
