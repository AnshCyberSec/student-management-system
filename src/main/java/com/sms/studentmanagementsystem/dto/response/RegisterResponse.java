package com.sms.studentmanagementsystem.dto.response;

import lombok.Getter;
import lombok.Setter;
import com.sms.studentmanagementsystem.entity.Role;



@Getter
@Setter

public class RegisterResponse {
    private Long id;

    private String username;

    private String email;

    private Role role;

    private boolean active;
}
