package com.muratapps.Student_Management.Entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "students")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Student {
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private  String firstName;
    private String lastName;
    @Column(nullable = false,unique = true)
    private String email;
}
