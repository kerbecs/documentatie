package com.java.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ServiceImpl implements ServiceInterface{

    @Autowired
    private TeacherDAOImp teacherDAOImp;

    @Override
    @Transactional
    public Teacher getTeacher(int id) {
        return teacherDAOImp.getTeacher(id);
    }

    @Override
    @Transactional
    public List<Teacher> getTeachersList() {
        return teacherDAOImp.getTeachersList();
    }
}
