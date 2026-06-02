package com.sms.studentmanagementsystem.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class StudentResponse {
    private Long id;
    private String studentCode;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private LocalDate dateOfBirth;
    private String gender;
    private String address;
    private String course;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
