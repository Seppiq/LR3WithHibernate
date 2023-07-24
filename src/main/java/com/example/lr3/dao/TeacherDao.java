package com.example.lr3.dao;

import com.example.lr3.model.TeacherHibernate;

import java.util.List;

public interface TeacherDao {

    List<TeacherHibernate> findAll();

    void save(TeacherHibernate user);
}
