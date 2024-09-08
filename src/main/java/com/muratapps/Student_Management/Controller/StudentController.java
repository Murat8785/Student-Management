package com.muratapps.Student_Management.Controller;


import com.muratapps.Student_Management.Dto.StudentDto;
import com.muratapps.Student_Management.Mapper.Mapper;
import com.muratapps.Student_Management.Service.StudentService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@AllArgsConstructor
public class StudentController {


    private StudentService studentService;

    @GetMapping("/studentList")
    public String getAllStudents(Model model){
        List<StudentDto> students=studentService.getAll();

        model.addAttribute("studentList",students);

        return "studentList" ;
    }

    @GetMapping("/studentList/new")
    public String newStudents(Model model){

      StudentDto studentDto=new StudentDto();
      model.addAttribute("student",studentDto);

       return "create_students";
    }

    @PostMapping("/studentList")
    public String createStudent(@Valid @ModelAttribute("student") StudentDto studentDto, BindingResult result,Model model){

        if (result.hasErrors()){
            model.addAttribute("student",studentDto);

            return "create_students";
        }

         studentService.createStudent(studentDto);

         return "redirect:/studentList";
    }
    @GetMapping("/studentList/{studentId}/edit")
    public String editStudent(@PathVariable("studentId") Long studentId ,Model model ){

        StudentDto studentDto=studentService.getStudentByID(studentId);
        model.addAttribute("student",studentDto);
        return "edit_students";
    }

    @PostMapping("/studentList/{studentId}")
    public String updateStudent(@PathVariable("studentId") Long studentId,@ModelAttribute("student") StudentDto studentDto,
                                BindingResult result,Model model){

        if (result.hasErrors()){
            model.addAttribute("student",studentDto);

            return "edit_students";
        }
        studentDto.setId(studentId);
       studentService.updateStudent(studentDto );

   return "redirect:/studentList";
    }


    @GetMapping("/studentList/{studentId}/delete")
    public String deleteStudent(@PathVariable("studentId") Long studentId ,Model model ){


       studentService.deleteStudent(studentId);

        return "redirect:/studentList";
    }

    @GetMapping("/studentList/{studentId}/view")
    public String viewStudents(@PathVariable("studentId") Long studentId,Model model){

     StudentDto studentDto=studentService.getStudentByID(studentId);
        model.addAttribute("student",studentDto);

        return "view_student";

    }


}
