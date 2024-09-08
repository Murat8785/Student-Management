package com.muratapps.Student_Management.Service;


import com.muratapps.Student_Management.Dto.StudentDto;
import com.muratapps.Student_Management.Entity.Student;
import com.muratapps.Student_Management.Mapper.Mapper;
import com.muratapps.Student_Management.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentServiceImp implements StudentService{

    @Autowired
    private   StudentRepository studentRepository;

    @Autowired
     private Mapper mapper;



    @Override
    public List<StudentDto> getAll() {

    List<Student> students=studentRepository.findAll();

      List<StudentDto> studentDtos=students.stream().map((student)->mapper.mapToStudentDto(student)).toList();

        return studentDtos;
    }

    @Override
    public StudentDto createStudent(StudentDto studentDto) {

        Student student=mapper.mapToStudent(studentDto);
        Student saveStudent=studentRepository.save(student);


        return mapper.mapToStudentDto(saveStudent);
    }

    @Override
    public StudentDto getStudentByID(Long studentId) {

        Student student=studentRepository.findById(studentId).get();


        return mapper.mapToStudentDto(student);
    }

    @Override
    public void updateStudent(StudentDto studentDto) {

        Student save = studentRepository.save(mapper.mapToStudent(studentDto));


    }

    @Override
    public void deleteStudent(Long studentId) {
        studentRepository.deleteById(studentId);
    }

}
