package com.spring.play.springboot_thymeleaf.repository;

import com.spring.play.springboot_thymeleaf.dto.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
}
