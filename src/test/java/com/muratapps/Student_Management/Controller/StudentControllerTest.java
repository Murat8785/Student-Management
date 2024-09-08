package com.muratapps.Student_Management.Controller;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.muratapps.Student_Management.Dto.StudentDto;
import com.muratapps.Student_Management.Service.StudentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import java.util.Arrays;

public class StudentControllerTest {

    @Mock
    private StudentService studentService;

    @Mock
    private Model model;

    @InjectMocks
    private StudentController studentController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(studentController).build();
    }

    @Test
    void testGetAllStudents() throws Exception {

        when(studentService.getAll()).thenReturn(Arrays.asList(new StudentDto(), new StudentDto()));


        mockMvc.perform(get("/studentList"))
                .andExpect(status().isOk())
                .andExpect(view().name("students"))
                .andExpect(model().attributeExists("students"));

        verify(studentService, times(1)).getAll();
        verify(model, times(1)).addAttribute(eq("students"), any());
    }

    @Test
    void testCreateStudent_WithValidationErrors() throws Exception {
        // Act & Assert
        mockMvc.perform(post("/studentList")
                        .flashAttr("student", new StudentDto()))  // Passing an invalid DTO object
                .andExpect(status().isOk())
                .andExpect(view().name("create_students"))
                .andExpect(model().attributeExists("student"));
    }

    @Test
    void testCreateStudent_Success() throws Exception {
        // Act & Assert
        mockMvc.perform(post("/studentList")
                        .flashAttr("student", new StudentDto(1L,"test","test","test@gmail.com")))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/studentList"));

        verify(studentService, times(1)).createStudent(any(StudentDto.class));
    }

    @Test
    void testEditStudent() throws Exception {

        Long studentId = 1L;
        when(studentService.getStudentByID(studentId)).thenReturn(new StudentDto());


        mockMvc.perform(get("/studentList/{studentId}/edit", studentId))
                .andExpect(status().isOk())
                .andExpect(view().name("edit_students"))
                .andExpect(model().attributeExists("student"));

        verify(studentService, times(1)).getStudentByID(studentId);
    }

    @Test
    void testUpdateStudent_WithValidationErrors() throws Exception {

        mockMvc.perform(post("/studentList/{studentId}", 1L)
                        .flashAttr("student", new StudentDto(1L,"test","test","test@gmail.com")))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/studentList"))
                .andExpect(model().attributeExists("student"));
    }

    @Test
    void testUpdateStudent_Success() throws Exception {

        mockMvc.perform(post("/studentList/{studentId}", 1L)
                        .flashAttr("student", new StudentDto(/* valid data */)))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/studentList"));

        verify(studentService, times(1)).updateStudent(any(StudentDto.class));
    }

    @Test
    void testDeleteStudent() throws Exception {

        Long studentId = 1L;


        mockMvc.perform(get("/studentList/{studentId}/delete", studentId))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/studentList"));

        verify(studentService, times(1)).deleteStudent(studentId);
    }

    @Test
    void testViewStudent() throws Exception {

        Long studentId = 1L;
        when(studentService.getStudentByID(studentId)).thenReturn(new StudentDto());


        mockMvc.perform(get("/studentList/{studentId}/view", studentId))
                .andExpect(status().isOk())
                .andExpect(view().name("view_student"))
                .andExpect(model().attributeExists("student"));

        verify(studentService, times(1)).getStudentByID(studentId);
    }
}