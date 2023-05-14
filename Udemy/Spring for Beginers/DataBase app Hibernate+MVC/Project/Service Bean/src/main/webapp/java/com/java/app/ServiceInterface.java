package com.java.app;

import java.util.List;

public interface ServiceInterface {
    Teacher getTeacher(int id);

    List<Teacher> getTeachersList();
}
