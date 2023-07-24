package com.example.lr3.service.impl;

import com.example.lr3.dao.impl.TeacherDaoImpl;
import com.example.lr3.model.TeacherHibernate;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TeacherServiceImplTest {

    @InjectMocks
    private TeacherServiceImpl teacherService;

    @Mock
    private TeacherDaoImpl teacherDao;

    @Test
    public void testFindAll() {
        TeacherHibernate teacher1 = new TeacherHibernate();
        TeacherHibernate teacher2 = new TeacherHibernate();
        List<TeacherHibernate> teachers = Arrays.asList(teacher1, teacher2);

        when(teacherDao.findAll()).thenReturn(teachers);

        List<TeacherHibernate> result = teacherService.findAll();

        assertEquals(2, result.size());
        assertEquals(teacher1, result.get(0));
        assertEquals(teacher2, result.get(1));
    }

    @Test
    public void testSave() {
        TeacherHibernate teacher = new TeacherHibernate();

        teacherService.save(teacher);

        verify(teacherDao).save(teacher);
    }
}