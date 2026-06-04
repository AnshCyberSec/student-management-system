package com.sms.studentmanagementsystem.service.impl;

import com.sms.studentmanagementsystem.dto.request.CreatePaymentRequest;
import com.sms.studentmanagementsystem.dto.request.PaymentFailedRequest;
import com.sms.studentmanagementsystem.dto.request.PaymentSuccessRequest;
import com.sms.studentmanagementsystem.dto.response.CreatePaymentResponse;
import com.sms.studentmanagementsystem.entity.Payment;
import com.sms.studentmanagementsystem.enums.PaymentStatus;
import com.sms.studentmanagementsystem.exception.ResourceNotFoundException;
import com.sms.studentmanagementsystem.repository.PaymentRepository;
import com.sms.studentmanagementsystem.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {
    private final PaymentRepository paymentRepository;
    @Override
    public CreatePaymentResponse createPayment(CreatePaymentRequest request) {
        String orderId = UUID.randomUUID().toString();

        Payment payment = new Payment();

        payment.setStudentCode(request.getStudentCode());
        payment.setAmount(request.getAmount());
        payment.setOrderId(orderId);
        payment.setStatus(PaymentStatus.PENDING);

        Payment savedPayment = paymentRepository.save(payment);

        return new CreatePaymentResponse(
                savedPayment.getOrderId(),
                savedPayment.getStudentCode(),
                savedPayment.getAmount(),
                savedPayment.getStatus().name()
        );
    }

    @Override
    public void markPaymentSuccess(PaymentSuccessRequest request) {
        Payment payment = paymentRepository.findByOrderId(request.getOrderId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Payment not found with order is: "
                         + request.getOrderId()
                ));
        payment.setPaymentId(request.getPaymentId());
        payment.setStatus(PaymentStatus.SUCCESS);
        paymentRepository.save(payment);
    }

    @Override
    public void markPaymentFailed(PaymentFailedRequest request) {
        Payment payment = paymentRepository
                .findByOrderId(request.getOrderId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Payment not found with order id: "
                                        + request.getOrderId()
                        ));

        payment.setStatus(PaymentStatus.FAILED);

        paymentRepository.save(payment);
    }

    @Override
    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }
}
