package com.sms.studentmanagementsystem.entity;

import com.sms.studentmanagementsystem.common.BaseEntity;
import com.sms.studentmanagementsystem.enums.PaymentStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "payments")
public class Payment extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String studentCode;

    @Column(nullable = false,precision = 10,scale = 2)
    private BigDecimal amount;

    @Column(unique = true)
    private String orderId;

    @Column(unique = true)
    private String paymentId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PaymentStatus status;
}
