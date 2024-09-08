package com.muratapps.Student_Management.Dto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudentDto {

    private Long id;
    @NotEmpty(message = "İsim Alanı Boş Bırakılamaz")
    private  String firstName;
    @NotEmpty(message = "Soyad Alanı Boş Bırakılamaz")
    private String lastName;
    @Email
    @NotEmpty(message = "Email Alanı Boş Bırakılamaz")
    private String email;
}
