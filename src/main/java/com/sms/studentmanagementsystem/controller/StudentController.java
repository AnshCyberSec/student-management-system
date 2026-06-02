package com.sms.studentmanagementsystem.controller;
import com.sms.studentmanagementsystem.common.ApiResponse;
import com.sms.studentmanagementsystem.dto.request.StudentCreateRequest;
import com.sms.studentmanagementsystem.dto.response.StudentResponse;
import com.sms.studentmanagementsystem.repository.StudentRepository;
import com.sms.studentmanagementsystem.service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;


    @PostMapping("/create")
    public ApiResponse<StudentResponse> createStudent(@Valid @RequestBody StudentCreateRequest request){
        StudentResponse student = studentService.createStudent(request);

        return new ApiResponse<>(
                true,
                "Student created successfully",
                student
        );
    }

    @GetMapping("/{id}")
    public ApiResponse<StudentResponse> getStudentById(@PathVariable Long id){
        StudentResponse student = studentService.getStudentById(id);
        return new ApiResponse<>(
                true,
                "Student fetched successfully",
                student
        );
    }

    @GetMapping
    public ApiResponse<List<StudentResponse>> getAllStudents(){
        List<StudentResponse> students = studentService.getAllStudents();

        return new ApiResponse<>(
                true,
                "Students fetched successfully",
                students
        );
    }

    @DeleteMapping("/{id}")
    public ApiResponse<String> deleteStudent(@PathVariable Long id){
        studentService.deleteStudent(id);
        return new ApiResponse<>(
                true,
                "Student deleted successfully",
                null
        );
    }
}
