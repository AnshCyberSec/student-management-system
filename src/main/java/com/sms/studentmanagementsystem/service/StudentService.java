package com.sms.studentmanagementsystem.service;

import com.sms.studentmanagementsystem.dto.request.StudentCreateRequest;
import com.sms.studentmanagementsystem.dto.request.StudentUpdateRequest;
import com.sms.studentmanagementsystem.dto.response.StudentResponse;

import java.util.List;

public interface StudentService {
    StudentResponse createStudent(StudentCreateRequest request);

    StudentResponse getStudentById(Long id);

    List<StudentResponse> getAllStudents();

    void deleteStudent(Long id);

    StudentResponse updateStudent(Long id, StudentUpdateRequest request);
}
