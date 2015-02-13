package com.example.mac.myapplication.backend;

/**
 * Created by mac on 10.02.15.
 * Id integer PRIMARY KEY AUTOINCREMENT,  Name text, URL TEXT, FileFolderLink int
 */
public class Content {
    int id;
    String name;
    String URL;
    int type;

    public Content(int id, String name, String URL, int type) {
        this.id = id;
        this.name = name;
        this.URL = URL;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Content() {

    }
}
