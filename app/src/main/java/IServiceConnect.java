///**
// * Created by mozartenok on 12.01.15.
// */
//public interface IServiceConnect {
//
//    public int login(String email, String password);
//    //метод отсылает запрос на сервер, где данные сравниваются с данными в базе данных
//    //если все правильно, мы получаем SessionID, который нужен для аутентификации всех послед. запросов
//
//    public boolean registered(User newUser);
//    // фрагмент регистрации создает объект пользователя и отправляет его на сервер.
//    // Если регистрация прошла успешно, возвращается true
//
//    public String[] getCountries();
//    // получаем список стран, которые есть в базе данных
//
//    public String[] getUniversities(String country);
//    // получаем список универов, которые есть в данной стране
//
//    public Group[] getGroupsInUniverstity(String UniversityName);
//    // Возвращает все группы в университете
//    // Group содержит в себе: имя, значок, дата создания, дата завершения обучения в группе
//    // описание.
//
//    public Group[] getGroupsBySearch(String GroupName);
//    // выводит группу при вводе в строку поиска (сложно)
//
//    public void createGroup(Group newGroup);
//    //создаем группу, данные закидываются в базу данных
//
//   public void createCourse(Course newCourse);
//    //-//-  как и в группе
//
//    public Course[] getCoursesInUniverstity(String UniversityName);
//    // Возвращает все группы в университете
//    // Group содержит в себе: имя, значок, дата создания, дата завершения обучения в группе
//    // описание.
//
//    public Course[] getCoursesBySearch(String GroupName);
//    // выводит группу при вводе в строку поиска (сложно)
//
//}
