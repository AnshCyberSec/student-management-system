package com.sms.studentmanagementsystem.repository;

import com.sms.studentmanagementsystem.entity.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student,Long> {
    Optional<Student> findByStudentCode(String studentCode);

    Optional<Student> findByEmail(String email);

    Optional<Student> findByPhoneNumber(String phoneNumber);

    boolean existsByStudentCode(String studentCode);

    boolean existsByEmail(String email);

    boolean existsByPhoneNumber(String phoneNumber);

    List<Student> findByFirstNameContainingIgnoreCase(String firstName);

    Page<Student> findByFirstNameContainingIgnoreCase(String keyword, Pageable pageable);

    @Query("""
       SELECT s
       FROM Student s
       WHERE LOWER(s.firstName) LIKE LOWER(CONCAT('%', :keyword, '%'))
          OR LOWER(s.lastName) LIKE LOWER(CONCAT('%', :keyword, '%'))
          OR LOWER(s.email) LIKE LOWER(CONCAT('%', :keyword, '%'))
          OR LOWER(s.studentCode) LIKE LOWER(CONCAT('%', :keyword, '%'))
       """)
    Page<Student> searchStudents(
            @Param("keyword") String keyword,
            Pageable pageable
    );

}
