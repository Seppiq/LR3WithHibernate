package com.example.lr3.service.impl;

import com.example.lr3.model.StudentProfile;
import com.example.lr3.repository.StudentProfileRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class StudentProfileServiceImplTest {

    @InjectMocks
    private StudentProfileServiceImpl studentProfileService;

    @Mock
    private StudentProfileRepository studentProfileRepository;

    @Test
    public void testFindAll() {
        StudentProfile profile1 = new StudentProfile();
        profile1.setAddress("Minsk");
        StudentProfile profile2 = new StudentProfile();
        List<StudentProfile> profiles = Arrays.asList(profile1, profile2);

        when(studentProfileRepository.findAll()).thenReturn(profiles);

        List<StudentProfile> result = studentProfileService.findAll();

        assertEquals(2, result.size());
        assertEquals(profile1.getAddress(), result.get(0).getAddress());
        assertEquals(profile2, result.get(1));
    }

    @Test
    public void testSave() {
        StudentProfile studentProfile = new StudentProfile();

        studentProfileService.save(studentProfile);

        verify(studentProfileRepository).save(studentProfile);

        assertEquals(LocalDate.now(), studentProfile.getDob());
    }

    @Test
    public void testFindById() {
        StudentProfile studentProfile = new StudentProfile();

        when(studentProfileRepository.findById(1L)).thenReturn(Optional.of(studentProfile));

        Optional<StudentProfile> result = studentProfileService.findById(1L);

        assertEquals(Optional.of(studentProfile), result);
    }
}