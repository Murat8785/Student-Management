package com.muratapps.Student_Management.Repository;

import com.muratapps.Student_Management.Entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student,Long> {
}
