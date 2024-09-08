package com.muratapps.Student_Management.Service;

import com.muratapps.Student_Management.Dto.StudentDto;

import java.util.List;

public interface StudentService {


    List<StudentDto> getAll();

    StudentDto createStudent(StudentDto studentDto);


    StudentDto getStudentByID(Long studentId);

    void updateStudent(StudentDto studentDto);

    void deleteStudent(Long studentId);
}
