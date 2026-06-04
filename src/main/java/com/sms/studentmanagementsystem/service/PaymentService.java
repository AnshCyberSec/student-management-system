package com.sms.studentmanagementsystem.service;

import com.sms.studentmanagementsystem.dto.request.CreatePaymentRequest;
import com.sms.studentmanagementsystem.dto.request.PaymentFailedRequest;
import com.sms.studentmanagementsystem.dto.request.PaymentSuccessRequest;
import com.sms.studentmanagementsystem.dto.response.CreatePaymentResponse;
import com.sms.studentmanagementsystem.entity.Payment;

import java.util.List;

public interface PaymentService {
    CreatePaymentResponse createPayment(CreatePaymentRequest request);

    void markPaymentSuccess(PaymentSuccessRequest request);

    void markPaymentFailed(
            PaymentFailedRequest request);

    List<Payment> getAllPayments();
}
