package com.sms.studentmanagementsystem.repository;

import com.sms.studentmanagementsystem.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student,Long> {
    Optional<Student> findByStudentCode(String studentCode);

    Optional<Student> findByEmail(String email);

    Optional<Student> findByPhoneNumber(String phoneNumber);

    boolean existsByStudentCode(String studentCode);

    boolean existsByEmail(String email);

    boolean existsByPhoneNumber(String phoneNumber);
}
