package com.sms.studentmanagementsystem.controller;

import com.sms.studentmanagementsystem.common.ApiResponse;
import com.sms.studentmanagementsystem.dto.request.CreatePaymentRequest;
import com.sms.studentmanagementsystem.dto.request.PaymentFailedRequest;
import com.sms.studentmanagementsystem.dto.request.PaymentSuccessRequest;
import com.sms.studentmanagementsystem.dto.response.CreatePaymentResponse;
import com.sms.studentmanagementsystem.entity.Payment;
import com.sms.studentmanagementsystem.service.PaymentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentService paymentService;

    @PostMapping
    public ApiResponse<CreatePaymentResponse> createPayment(@Valid @RequestBody CreatePaymentRequest request){
        CreatePaymentResponse response = paymentService.createPayment(request);

        return new ApiResponse<>(
                true,
                "Payment order created successfully",
                response
        );
    }

    @PostMapping("/success")
    public ApiResponse<String> markPaymentSuccess(@Valid @RequestBody PaymentSuccessRequest request){
        paymentService.markPaymentSuccess(request);
        return new ApiResponse<>(
                true,
                "Payment marked as successful",
                "SUCCESS"
        );
    }
    @PostMapping("/failed")
    public ApiResponse<String> markPaymentFailed(
            @Valid @RequestBody PaymentFailedRequest request) {

        paymentService.markPaymentFailed(request);

        return new ApiResponse<>(
                true,
                "Payment marked as failed",
                "FAILED"
        );
    }
    @GetMapping
    public ApiResponse<List<Payment>> getAllPayments() {

        List<Payment> payments =
                paymentService.getAllPayments();

        return new ApiResponse<>(
                true,
                "Payments fetched successfully",
                payments
        );
    }
}
