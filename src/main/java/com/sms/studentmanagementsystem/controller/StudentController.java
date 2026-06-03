package com.sms.studentmanagementsystem.controller;
import com.sms.studentmanagementsystem.common.ApiResponse;
import com.sms.studentmanagementsystem.dto.request.StudentCreateRequest;
import com.sms.studentmanagementsystem.dto.request.StudentUpdateRequest;
import com.sms.studentmanagementsystem.dto.response.PaginationResponse;
import com.sms.studentmanagementsystem.dto.response.StudentResponse;
import com.sms.studentmanagementsystem.repository.StudentRepository;
import com.sms.studentmanagementsystem.service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
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

    @PutMapping("/{id}")
    public ApiResponse<StudentResponse> updateStudent(@PathVariable Long id, @Valid @RequestBody StudentUpdateRequest request){
        StudentResponse response = studentService.updateStudent(id,request);
        return new ApiResponse<>(
                true,
                "Student updated successfully",
                response
        );
    }

    @GetMapping("/search")
    public ApiResponse<PaginationResponse<StudentResponse>> searchStudents(@RequestParam String keyword,
                                                             @RequestParam(defaultValue = "0") int page,
                                                             @RequestParam(defaultValue = "5") int size,
                                                             @RequestParam(defaultValue = "firstName") String sortBy,
                                                             @RequestParam(defaultValue = "asc") String direction){

        Page<StudentResponse> students = studentService.searchStudents(keyword,page,size,sortBy,direction);

        PaginationResponse<StudentResponse> response = new PaginationResponse<>();

        response.setData(students.getContent());
        response.setCurrentPage(students.getNumber());
        response.setPageSize(students.getSize());
        response.setTotalElements(students.getTotalElements());
        response.setTotalPages(students.getTotalPages());
        response.setHasNext(students.hasNext());
        response.setHasPrevious(students.hasPrevious());

        return new ApiResponse<>(
                true,
                "Students fetched successfully",
                response
        );
    }

}
