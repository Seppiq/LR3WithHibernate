package com.example.lr3.service.impl;

import com.example.lr3.dao.TeacherDao;
import com.example.lr3.model.TeacherHibernate;
import com.example.lr3.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    private TeacherDao teacherDao;

    public TeacherServiceImpl(TeacherDao teacherDao) {
        this.teacherDao = teacherDao;
    }

    @Override
    public void save(TeacherHibernate teacher) {
        teacherDao.save(teacher);
    }

    @Override
    public List<TeacherHibernate> findAll() {
        return teacherDao.findAll();
    }
}
