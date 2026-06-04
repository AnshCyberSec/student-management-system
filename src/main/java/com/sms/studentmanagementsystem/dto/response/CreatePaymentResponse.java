package com.sms.studentmanagementsystem.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
public class CreatePaymentResponse {
    private String orderId;

    private String studentCode;

    private BigDecimal amount;

    private String status;
}
