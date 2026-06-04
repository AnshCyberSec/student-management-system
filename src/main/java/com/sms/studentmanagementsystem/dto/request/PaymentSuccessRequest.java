package com.sms.studentmanagementsystem.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentSuccessRequest {
    @NotBlank(message = "Order is is required")
    private String orderId;

    @NotBlank(message = "Payment is is required")
    private String paymentId;
}
