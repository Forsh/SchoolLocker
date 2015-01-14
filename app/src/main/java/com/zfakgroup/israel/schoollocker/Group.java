package com.zfakgroup.israel.schoollocker;

import android.content.Context;

/**
 * Created by mac on 14.01.15.
 *
 * Заглушка для класса группы. Наследует Search Result потому что у групп и курсов есть общие поля (???)
 *
 */
public class Group extends SearchResult{
    Course[] courses;
    User[] users;
    public Group(String name, String description, String imageURL, Course[] courses, User[] users,Context context) {
        super(name, description, imageURL, context);
        this.courses = courses;
        this.users = users;
    }
}
