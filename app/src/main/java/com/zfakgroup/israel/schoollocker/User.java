package com.zfakgroup.israel.schoollocker;

/**
 * Created by mac on 14.01.15.
 */
public class User {
    private String name;
    private String email;
    public User(String name, String email, String nickname){
        this.setName(name);
        this.setEmail(email);
    }

    public User(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
