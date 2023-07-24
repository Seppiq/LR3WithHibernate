package com.example.lr3.service;

import com.example.lr3.model.TeacherHibernate;

import java.util.List;

public interface TeacherService {
    void save(TeacherHibernate teacher);

    List<TeacherHibernate> findAll();
}
