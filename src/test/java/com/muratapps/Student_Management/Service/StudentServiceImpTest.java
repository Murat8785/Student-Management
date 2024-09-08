package com.muratapps.Student_Management.Service;

import com.muratapps.Student_Management.Dto.StudentDto;
import com.muratapps.Student_Management.Entity.Student;
import com.muratapps.Student_Management.Mapper.Mapper;
import com.muratapps.Student_Management.Repository.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

class StudentServiceImpTest {

    @InjectMocks
    private StudentServiceImp studentServiceImp;

    @Mock
    private StudentRepository studentRepository;

    @Mock
    private Mapper mapper;

    @BeforeEach
    void setUp() {
        mapper=new Mapper();
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAll() {
        List<Student> students=new ArrayList<>();
        students.add(new Student(1L,"test","test","test@gmail.com"));

        Mockito.when(studentRepository.findAll()).thenReturn(students);

        List<StudentDto> studentDtos=studentServiceImp.getAll();

       assertEquals(students.size(),studentDtos.size());

       Mockito.verify(studentRepository,Mockito.times(1)).findAll();

    }
    @Test
    void createStudent() {
        Student student = new Student(1L, "test", "test", "test@gmail.com");
       StudentDto studentDto= new StudentDto(1L,"test","test","test@gmail.com");
        Student savestudent = new Student(1L, "test", "test", "test@gmail.com");

      Mockito.when(mapper.mapToStudent(studentDto)) .thenReturn(student);
      Mockito.when(studentRepository.save(student)).thenReturn(savestudent);
      Mockito.when(mapper.mapToStudentDto(savestudent)).thenReturn(new StudentDto(1L,"test","test","test@gmail.com"));

      StudentDto studentDto1=studentServiceImp.createStudent(studentDto);

      assertEquals(savestudent.getId(),studentDto1.getId());
      assertEquals(savestudent.getFirstName(),studentDto1.getFirstName());
      assertEquals(savestudent.getLastName(),studentDto1.getLastName());
      assertEquals(savestudent.getEmail(),studentDto1.getEmail());

      Mockito.verify(mapper,Mockito.times(1)).mapToStudent(studentDto);
     Mockito.verify(studentRepository,Mockito.times(1 )).save(student);
        Mockito.verify(mapper,Mockito.times(1)).mapToStudentDto(savestudent);


    }

    @Test
    void getStudentByID() {

        Student student = new Student(1L, "test", "test", "test@gmail.com");
        StudentDto studentDto = new StudentDto(1L, "test", "test", "test@gmail.com");
        Mockito.when(studentRepository.findById(studentDto.getId())).thenReturn(Optional.of(student));
        Mockito.when(mapper.mapToStudentDto(student)).thenReturn(new StudentDto(1L, "test", "test", "test@gmail.com"));

        StudentDto studentDto1=studentServiceImp.getStudentByID(student.getId());

        assertEquals(studentDto1.getId(),studentDto.getId());

        Mockito.verify(studentRepository,Mockito.times(1)).findById(studentDto.getId());
        Mockito.verify(mapper,Mockito.times(1)).mapToStudentDto(student);


    }

    @Test
    void updateStudent() {
        Student student = new Student(1L, "test", "Test", "test@gmail.com");
        StudentDto studentDto = new StudentDto(1L, "test", "test", "test@gmail.com");
        Student updatestudent = new Student(studentDto.getId(),studentDto.getFirstName(),studentDto.getLastName(),studentDto.getEmail());

        Mockito.when(studentRepository.save(student)).thenReturn(student);

        studentServiceImp.updateStudent(studentDto);

        assertEquals(studentDto.getId(),updatestudent.getId());
        assertEquals(studentDto.getFirstName(),updatestudent.getFirstName());
        assertEquals(studentDto.getLastName(),updatestudent.getLastName());
        assertEquals(studentDto.getEmail(),updatestudent.getEmail());






    }


}