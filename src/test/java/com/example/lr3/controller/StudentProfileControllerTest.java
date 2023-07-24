package com.example.lr3.controller;

import com.example.lr3.model.StudentProfile;
import com.example.lr3.service.StudentProfileService;
import com.example.lr3.utils.TestUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.internal.verification.VerificationModeFactory.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(value = StudentProfileController.class)
public class StudentProfileControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StudentProfileService studentProfileService;

    @Test
    public void testGetStudentProfiles() throws Exception {

        StudentProfile profile1 = new StudentProfile();
        profile1.setAddress("Minsk");
        StudentProfile profile2 = new StudentProfile();
        profile2.setAddress("Mogilev");

        List<StudentProfile> profiles = Arrays.asList(profile1, profile2);

        given(studentProfileService.findAll()).willReturn(profiles);

        mockMvc.perform(get("/api/profile"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].address", is("Minsk")))
                .andExpect(jsonPath("$[1].address", is("Mogilev")));
    }

    @Test
    public void testSaveStudentProfile() throws Exception {
        StudentProfile profile = new StudentProfile();

        mockMvc.perform(post("/api/profile")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(TestUtil.convertObjectToJsonBytes(profile)))
                .andExpect(status().isOk());

        verify(studentProfileService, times(1)).save(profile);
    }
}