package com.sms.studentmanagementsystem.entity;

import com.sms.studentmanagementsystem.common.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(
        name = "students",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "student_code"),
                @UniqueConstraint(columnNames = "email"),
                @UniqueConstraint(columnNames = "phone_number")
        }
)
public class Student extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "student_code",nullable = false,length = 20)
    private String studentCode;

    @Column(name = "first_name",nullable = false,length = 50)
    private String firstName;

    @Column(name = "last_name",nullable = false,length = 50)
    private String lastName;

    @Column(nullable = false,length = 100)
    private String email;

    @Column(name = "phone_number",nullable = false,length = 15)
    private String phoneNumber;

    @Column(name = "date_of_birth",nullable = false)
    private LocalDate dateOfBirth;

    @Column(nullable = false,length = 10)
    private String gender;

    @Column(nullable = false,length = 255)
    private String address;

    @Column(nullable = false,length = 100)
    private String course;
}
