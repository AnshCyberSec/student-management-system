package com.sms.studentmanagementsystem.dto.request;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class StudentCreateRequest {

    @NotBlank(message = "Student code is required")
    @Size(max = 20,message = "Student code cannot exceed 20 characters")
    private String studentCode;

    @NotBlank(message = "First name is required")
    @Size(max = 50,message = "First name cannot exceed 50 characters")
    private String firstName;

    @NotBlank(message = "Last name is required")
    @Size(max = 50,message = "Last name cannot exceed 50 characters")
    private String lastName;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    @Size(max = 100,message = "Email cannot exceed 100 characters")
    private String email;

    @NotBlank(message = "Phone number is required")
    @Pattern(
            regexp = "^[0-9]{10}$",
            message = "Phone numer must be exactly 10 digits"
    )
    private String phoneNumber;

    @NotNull(message = "Date of birth is required")
    private LocalDate dateOfBirth;

    @NotBlank(message = "Gender is required")
    private String gender;

    @NotBlank(message = "Address is required")
    @Size(max = 255,message = "Address cannot exceed 255 characters")
    private String address;

    @NotBlank(message = "Course is required")
    @Size(max = 100,message = "Course cannot exceed 100 characters")
    private String course;
}
