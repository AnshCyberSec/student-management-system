package com.sms.studentmanagementsystem.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentFailedRequest {
    @NotBlank(message = "Order id is required")
    private String orderId;
}
