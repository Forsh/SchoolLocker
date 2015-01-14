package com.zfakgroup.israel.schoollocker;

import android.content.Context;

import java.io.File;

/**
 * Created by mac on 14.01.15.
 */
public class Course extends SearchResult{
    String[] files;
    String[] folders;
    String[] links;
    public Course(String name, String description, String imageURL, String[] files, String[] folders, String[] links, Context context) {
        super(name, description, imageURL, context);
        this.folders = folders;
        this.files = files;
        this.links = links;
    }
}
