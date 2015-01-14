CREATE TABLE USERS(Id integer PRIMARY KEY AUTOINCREMENT, Name text, Email text, Password text);

CREATE TABLE COURSES(Id integer PRIMARY KEY AUTOINCREMENT,  Name text, Description text, Beginning text, Ending text);

CREATE TABLE GROUPS(Id integer PRIMARY KEY AUTOINCREMENT,  Name text, Description text);

CREATE TABLE UNIVERSITIES(Id integer PRIMARY KEY AUTOINCREMENT,  Name text, Description text, Adress text);

CREATE TABLE COUNTRIES(Id integer PRIMARY KEY AUTOINCREMENT,  Name text);

CREATE TABLE CONTENTS(Id integer PRIMARY KEY AUTOINCREMENT,  Name text, URL TEXT, FileFolderLink int);


CREATE TABLE GROUPSINUNIVERSITIES(GroupId integer NOT NULL, UniversityId integer NOT NULL,FOREIGN KEY(GroupId) REFERENCES GROUPS(Id),FOREIGN KEY(UniversityId) REFERENCES UNIVERSITIES(Id));

CREATE TABLE COURSESINUNIVERSITIES(CourseId integer NOT NULL, UniversityId integer NOT NULL,FOREIGN KEY(CourseId) REFERENCES COURSES(Id),FOREIGN KEY(UniversityId) REFERENCES UNIVERSITIES(Id));

CREATE TABLE FILESINCOURSES(FileId integer NOT NULL, CourseId integer NOT NULL,FOREIGN KEY(CourseId) REFERENCES COURSES(Id),FOREIGN KEY(FileID) REFERENCES CONTENTS(Id));

CREATE TABLE GROUPSINCOURSES(GroupId integer NOT NULL, CourseId integer NOT NULL,FOREIGN KEY(GroupId) REFERENCES GROUPS(Id),FOREIGN KEY(CourseId) REFERENCES COURSES(Id));

CREATE TABLE USERSINGROUPS(UserId integer NOT NULL, GroupId integer NOT NULL, Rights integer,FOREIGN KEY(GroupId) REFERENCES GROUPS(Id),FOREIGN KEY(UserId) REFERENCES USERS(Id));

CREATE TABLE USERSINCOURSES(UserId integer NOT NULL, CourseId integer NOT NULL, Rights integer,FOREIGN KEY(UserId) REFERENCES USERS(Id),FOREIGN KEY(CourseId) REFERENCES COURSES(Id));

SELECT name FROM sqlite_master WHERE type='table';

INSERT INTO USERS(Name, Email, Password) VALUES("Alexander Forshtat", "forshtat@bk.ru", "abcd1234");

INSERT INTO USERS(Name, Email, Password) VALUES("Illya Krit", "forshtat@bk.ru", "abcd1234");

INSERT INTO USERS(Name, Email, Password) VALUES("Alexandra Alymova", "forshtat@bk.ru", "abcd1234");

INSERT INTO USERS(Name, Email, Password) VALUES("Dmitri Zolotarev", "forshtat@bk.ru", "abcd1234");

INSERT INTO COURSES(Name, Description, Beginning, Ending) VALUES("Android Development", "Sergei is going to turn you into great programmers using UML and magic", "2014-09-01", "2015-07-01");

INSERT INTO COURSES(Name, Description) VALUES("Nuclear phisics", "Harvard University course leads you through newest nuclear phisics theories");

INSERT INTO GROUPS(Name, Description) VALUES("Masa Matrix 2014", "Jewish students from FSU countries");

INSERT INTO GROUPS(Name, Description) VALUES("Other", "Random people");

INSERT INTO UNIVERSITIES(Name, Description, Adress) VALUES("John Bryce", "IT College", "Haifa");

INSERT INTO UNIVERSITIES(Name, Description, Adress) VALUES("Harvard University", "Best university since 1636", "Cambridge");

INSERT INTO COUNTRIES(Name) VALUES("United States");

INSERT INTO COUNTRIES(Name) VALUES("Israel");

INSERT INTO GROUPSINUNIVERSITIES VALUES(1,1);
INSERT INTO COURSESINUNIVERSITIES VALUES(1,1);
INSERT INTO GROUPSINCOURSES VALUES(1,1);
INSERT INTO USERSINGROUPS VALUES(1,1,0);
INSERT INTO USERSINCOURSES VALUES(1,1,0);
INSERT INTO USERSINCOURSES VALUES(2,1,0);
INSERT INTO USERSINCOURSES VALUES(3,1,0);
INSERT INTO USERSINCOURSES VALUES(4,1,0);






