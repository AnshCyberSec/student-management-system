package com.sms.studentmanagementsystem.service.impl;

import com.sms.studentmanagementsystem.dto.request.StudentCreateRequest;
import com.sms.studentmanagementsystem.dto.request.StudentUpdateRequest;
import com.sms.studentmanagementsystem.dto.response.StudentResponse;
import com.sms.studentmanagementsystem.entity.Student;
import com.sms.studentmanagementsystem.exception.DuplicateResourceException;
import com.sms.studentmanagementsystem.exception.ResourceNotFoundException;
import com.sms.studentmanagementsystem.mapper.StudentMapper;
import com.sms.studentmanagementsystem.repository.StudentRepository;
import com.sms.studentmanagementsystem.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;


    @Override
    public StudentResponse createStudent(StudentCreateRequest request) {
       Student student =  studentMapper.toEntity(request);

       Student savedStudent = studentRepository.save(student);

       return studentMapper.toResponse(savedStudent);
    }

    @Override
    public StudentResponse getStudentById(Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id: " + id));
        return studentMapper.toResponse(student);
    }

    @Override
    public List<StudentResponse> getAllStudents() {
        return studentRepository.findAll()
                .stream()
                .map(studentMapper::toResponse)
                .toList();
    }

    @Override
    public void deleteStudent(Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Student not found with id : " + id));
        studentRepository.delete(student);
    }

    @Override
    public StudentResponse updateStudent(Long id, StudentUpdateRequest request) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Student not found with id: " + id
                ));

        if (!student.getEmail().equals(request.getEmail()) && studentRepository.existsByEmail(request.getEmail())){
            throw new DuplicateResourceException(
                    "Email already exists"
            );

        }
        if (!student.getPhoneNumber().equals(request.getPhoneNumber()) && studentRepository.existsByPhoneNumber(request.getPhoneNumber())){
            throw new DuplicateResourceException(
                    "Phone number already exists"
            );
        }

        student.setFirstName(request.getFirstName());
        student.setLastName(request.getLastName());
        student.setEmail(request.getEmail());
        student.setPhoneNumber(request.getPhoneNumber());
        student.setDateOfBirth(request.getDateOfBirth());
        student.setGender(request.getGender());
        student.setAddress(request.getAddress());
        student.setCourse(request.getCourse());

        Student updatedStudent = studentRepository.save(student);

        return studentMapper.toResponse(updatedStudent);
    }

    @Override
    public Page<StudentResponse> searchStudents(String keyword,int page,int size,String sortBy,String direction) {

        Sort sort = direction.equalsIgnoreCase("desc")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();

        Pageable pageable = PageRequest.of(page,size,sort);

        Page<Student> studentPage =
                studentRepository.searchStudents(
                        keyword,
                        pageable
                );
        List<StudentResponse> responses = studentPage.getContent()
                .stream()
                .map(studentMapper::toResponse)
                .toList();

        return new PageImpl<>(
                responses,
                pageable,
                studentPage.getTotalElements()
        );

    }
}
