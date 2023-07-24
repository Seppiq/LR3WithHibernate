package com.example.lr3.dao.impl;

import com.example.lr3.dao.TeacherDao;
import com.example.lr3.model.TeacherHibernate;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TeacherDaoImpl implements TeacherDao {

    @Autowired
    private EntityManagerFactory entityManagerFactory;


    @Override
    public List<TeacherHibernate> findAll() {
        Session session = entityManagerFactory.unwrap(SessionFactory.class).openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<TeacherHibernate> criteria = builder.createQuery(TeacherHibernate.class);
        Root<TeacherHibernate> contactRoot = criteria.from(TeacherHibernate.class);
        criteria.select(contactRoot);
        return session.createQuery(criteria).getResultList();
    }

    @Override
    public void save(TeacherHibernate teacher) {
        Session session = entityManagerFactory.unwrap(SessionFactory.class).openSession();

        session.save(teacher);

        session.close();
    }
}
