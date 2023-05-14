package com.java.app;

import java.util.List;

public interface TeacherDAO {
    Teacher getTeacher(int id);

    List<Teacher> getTeachersList();
}
