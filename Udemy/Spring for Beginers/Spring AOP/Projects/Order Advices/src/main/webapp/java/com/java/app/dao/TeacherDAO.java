package com.java.app.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Repository
public class TeacherDAO {
    @Autowired
    private Teacher teacher;

    public List<Teacher> vipTeachers;

    public List<Teacher> findVipTeachers(){
        if(vipTeachers==null)
            throw new RuntimeException("Teachers list is null!!!");

        vipTeachers.add(new Teacher("Mititiuc","Eduard"));
        vipTeachers.add(new Teacher("John","Bree"));
        vipTeachers.add(new Teacher("Alice","Soson"));

        return vipTeachers;
    }


    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public List<Teacher> getVipTeachers() {
        return vipTeachers;
    }

    public void setVipTeachers(List<Teacher> vipTeachers) {
        this.vipTeachers = vipTeachers;
    }
}
