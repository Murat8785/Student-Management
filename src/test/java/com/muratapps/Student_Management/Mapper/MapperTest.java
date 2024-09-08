package com.muratapps.Student_Management.Mapper;

import com.muratapps.Student_Management.Dto.StudentDto;
import com.muratapps.Student_Management.Entity.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;

class MapperTest {

    private Mapper mapper;
    @BeforeEach
    void setUp() {

        mapper=new Mapper();
    }

    @Test
    void mapToStudent() {
        StudentDto student=new StudentDto(1L,"test","test","test@gmail.com");

        Student student1=mapper.mapToStudent(student);

        assertEquals(student1.getId(),student.getId());
        assertNotNull(student1.getId());
        assertEquals(student1.getFirstName(),student.getFirstName());
        assertEquals(student1.getLastName(),student.getLastName());
        assertEquals(student1.getEmail(),student.getEmail());

    }

    @Test
    void mapToStudentDto() {

        Student student=new Student(1L,"test","test","test@gmail.com");

        StudentDto student1=mapper.mapToStudentDto(student);

        assertEquals(student1.getId(),student.getId());
        assertNotNull(student1.getId());
        assertEquals(student1.getFirstName(),student.getFirstName());
        assertEquals(student1.getLastName(),student.getLastName());
        assertEquals(student1.getEmail(),student.getEmail());


    }
}