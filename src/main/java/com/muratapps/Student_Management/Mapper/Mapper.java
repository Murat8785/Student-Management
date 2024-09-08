package com.muratapps.Student_Management.Mapper;

import com.muratapps.Student_Management.Dto.StudentDto;
import com.muratapps.Student_Management.Entity.Student;

public class Mapper {

    public   Student mapToStudent(StudentDto studentDto){

        return new Student(
                studentDto.getId(),
                studentDto.getFirstName(),
                studentDto.getLastName(),
                studentDto.getEmail()
        );
    }


    public  StudentDto mapToStudentDto(Student student){
        return new StudentDto(
                student.getId(),
                student.getFirstName(),
                student.getLastName(),
                student.getEmail()
        );
    }


}
