//package com.example.lr3.utils;
//
//import com.example.lr3.model.Student;
//import com.example.lr3.model.StudentAssignment;
//import com.example.lr3.model.TeacherHibernate;
//import org.hibernate.SessionFactory;
//import org.hibernate.boot.MetadataSources;
//import org.hibernate.boot.registry.StandardServiceRegistry;
//import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
//import org.hibernate.cfg.Configuration;
//import org.springframework.beans.factory.annotation.Autowired;
//
//public class HibernateSessionFactoryUtil {
//
//    private static SessionFactory sessionFactory;
//
//    public HibernateSessionFactoryUtil() {
//    }
//
//    public static SessionFactory getSessionFactory() {
//        if (sessionFactory == null) {
//            try {
//                Configuration configuration = new Configuration().configure();
//                configuration.addAnnotatedClass(TeacherHibernate.class);
////                configuration.addAnnotatedClass(Student.class);
////                configuration.addAnnotatedClass(StudentAssignment.class);
//                StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
//                sessionFactory = configuration.buildSessionFactory(builder.build());
//
//            } catch (Exception e) {
//                System.out.println("Исключение!" + e);
//            }
//        }
//        return sessionFactory;
//    }
//
//
//}