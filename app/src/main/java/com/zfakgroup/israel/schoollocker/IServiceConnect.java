package com.zfakgroup.israel.schoollocker;

import com.example.mac.myapplication.backend.myApi.model.Course;
import com.example.mac.myapplication.backend.myApi.model.Group;
import com.example.mac.myapplication.backend.myApi.model.User;
import com.zfakgroup.israel.schoollocker.asynctasks.AsyncCallback;

import java.util.HashMap;

/**
* Created by mozartenok on 12.01.15.
*/
public interface IServiceConnect {

    public HashMap<String, AsyncCallback> listeners = new HashMap<>();

    public void setListener(String name, AsyncCallback listener);
    // Так как все действия выполняются асинхронно, их последствия должны быть известны обраблотчикам.
    // Возвращать значения операции не должны (!!!)


    public void login(String email, String password);
    //метод отсылает запрос на сервер, где данные сравниваются с данными в базе данных
    //если все правильно, мы получаем SessionID, который нужен для аутентификации всех послед. запросов

    public boolean register(User newUser);
    // фрагмент регистрации создает объект пользователя и отправляет его на сервер.
    // Если регистрация прошла успешно, возвращается true

    public String[] getCountries();
    // получаем список стран, которые есть в базе данных

    public String[] getUniversities(String country);
    // получаем список универов, которые есть в данной стране

    //public Group[] getGroupsInUniversity(String UniversityName);
    // Возвращает все группы в университете
    // Group содержит в себе: имя, значок, дата создания, дата завершения обучения в группе
    // описание.

    public Group[] getGroupsBySearch(String GroupName);
    // выводит группу при вводе в строку поиска (сложно), возвращает все группы с похожими названиями и выводит их в выборе

    public void createGroup(Group newGroup);
    //создаем группу, данные закидываются в базу данных

   public void createCourse(Course newCourse);
    //-//-  как и в группе

    //public Course[] getCoursesInUniversity(String UniversityName);
    // Возвращает все группы в университете
    // Group содержит в себе: имя, значок, дата создания, дата завершения обучения в группе
    // описание.

    public Course[] getCoursesBySearch(String GroupName);
    // выводит группу при вводе в строку поиска (сложно)

    public Course[] getCoursesOfMyGroup(String GroupName);
    //выводит все курсы группы

    public Course[] getMyCourses(String UserName);
    // находит имя пользователя в базе данных и выводит все группы в которых он состоит

    public User searchUser(String firstName, String lastName);
    //находит пользователя, возвращает все его группы и данные

    public Group searchGroup(String Group);
    //находит группу

    public Course searchCourse(String course);
    //находит курс
}
