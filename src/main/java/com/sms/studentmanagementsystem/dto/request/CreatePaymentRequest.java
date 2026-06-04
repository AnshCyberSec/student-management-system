package com.sms.studentmanagementsystem.dto.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class CreatePaymentRequest {
    @NotBlank(message = "Student code is required")
    private String studentCode;

    @DecimalMin(value = "1.0",message = "Amount must be greater than 0")
    private BigDecimal amount;
}
